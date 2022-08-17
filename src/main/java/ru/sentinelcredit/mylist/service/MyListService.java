package ru.sentinelcredit.mylist.service;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.sentinelcredit.mylist.model.OperationColumn;
import ru.sentinelcredit.mylist.model.MyList;
import ru.sentinelcredit.mylist.repository.ColumnRepository;
import ru.sentinelcredit.mylist.repository.MyListRepository;
import ru.sentinelcredit.mylist.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyListService {
    private final UserRepository userRepository;
    private final ColumnRepository columnRepository;
    private final MyListRepository myListRepository;
    private String userId;
//    private String jobId;
    private String operationName;
//    private String newJobId = "";
    private List<OperationColumn> operationColumnList;
    private HttpSession session;

    private MyList rowJsonToMyList (JSONObject jsonObject) throws Exception {
        MyList myList = new MyList();

        boolean isData = false;
        for (OperationColumn column: operationColumnList) {
            if (column.getColumnNum() == null || column.getColumnNum() == 0) {
                log.error("Ошибка в описании операции. userId={}. jobId={}. operationName={}. operationColumnList={}. jsonObject={}.",
                        userId, session.getAttribute("jobId"), operationName, operationColumnList, jsonObject);
                throw new Exception("Ошибка в описании операции.");
            }

            if (jsonObject.has("cells")) {
                JSONObject jsonCells = jsonObject.getJSONObject("cells");
                String columnNum = Integer.toString(column.getColumnNum()-1);
                if (jsonCells.has(columnNum)) {
                    JSONObject jsonCell = jsonCells.getJSONObject(columnNum);
                    boolean isSet = myList.setCColumn(column.getColumnId(), jsonCell.getString("text"));
                    isData = (isData || isSet);
                }
            }
        }

        if (!isData) {
            myList = null;
            return null;
        }

        myList.setCreatedBy(userId);
        myList.setLastUpdBy(userId);
        myList.setJobId(session.getAttribute("jobId").toString());
        myList.setTypeCd(operationName);
        myList.setDbLastUpdSrc("JPA_MY_LIST");

        return myList;
    }

    private boolean saveData (String data) throws Exception {
        List<MyList> myListList = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(data);

        log.trace("block");

        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            Object object = jsonObject.get(key);
            if (object instanceof JSONObject) {
                MyList myList = rowJsonToMyList((JSONObject) object);
                if (myList instanceof MyList)
                    myListList.add(myList);
            }
        }

        log.trace("size={}", myListList.size());

        if (myListList.size() == 0)
            return false;

        log.trace("saveAll jobId={} start", session.getAttribute("jobId"));

        myListRepository.saveAll(myListList);

        log.trace("saveAll end");

        return true;
    }

    private JSONArray extractData(List<MyList> myListList) {
        JSONObject jsonRows = new JSONObject();

        for (int i = 0; i < myListList.size(); i ++) {
            JSONObject jsonCells = new JSONObject();
            JSONObject jsonCell = new JSONObject();

            String value = myListList.get(i).getStatus();
            if (!(value instanceof String))
                value = "";

            JSONObject jsonStatus = new JSONObject();
            jsonStatus.put("text", value);
            jsonStatus.put("style", 0);
            jsonCell.put("0", jsonStatus);

            value = myListList.get(i).getComments();
            if (!(value instanceof String))
                value = "";

            JSONObject jsonComment = new JSONObject();
            jsonComment.put("text", value);
            jsonCell.put("1", jsonComment);

            for (OperationColumn column: operationColumnList) {
                value = myListList.get(i).getCColumn(column.getColumnId());
                if (value instanceof String && value.length() > 0) {
                    JSONObject jsonText = new JSONObject();
                    jsonText.put("text", value);
                    String columnNum = Integer.toString(column.getColumnNum() + 1);
                    jsonCell.put(columnNum, jsonText);
                }
            }

            jsonCells.put("cells", jsonCell);
            jsonRows.put(Integer.toString(i), jsonCells);
        }

        jsonRows.put("len",  myListList.size());

        JSONObject jsonData = new JSONObject();
        jsonData.put("rows", jsonRows);

        JSONObject jsonBold = new JSONObject();
        jsonBold.put("bold", "true");
        JSONObject jsonFont = new JSONObject();
        jsonFont.put("font", jsonBold);
        JSONArray jsonStyles = new JSONArray();
        jsonStyles.put(jsonFont);
        jsonData.put("styles", jsonStyles);

        JSONObject jsonCols = new JSONObject();
        jsonCols.put("len", operationColumnList.size()+2);
        jsonData.put("cols", jsonCols);

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonData);

        return jsonArray;
    }

    @Transactional (Transactional.TxType.SUPPORTS)
    public String executeData (String login, String operationName, MultipartFile[] data, String marker, String campaign, HttpSession session) {
        log.trace("executeData start login={} operation={} marker={} campaign={} data={}", login, operationName, marker, campaign, data.length);

        try {
            this.userId = userRepository.findByLogin(login).get(0).getRowId();
//            this.jobId = myListRepository.getNextSblLikeId();
            this.operationName = operationName;
            this.operationColumnList = columnRepository.findByOperationName(operationName);
/*
            this.newJobId = (operationName.equals("Подтягивание разной даты в отчётный файл") ||
                    operationName.equals("Выгрузка телефонов") ||
                    operationName.equals("Импорт телефонов") ||
                    operationName.equals("Выгрузка активностей 2") ||
                    operationName.equals("Выгрузка активностей") ||
                    operationName.equals("Отчет по откликам")) ? this.newJobId = myListRepository.getNextSblLikeId() : "";
*/
            session.setAttribute("jobId", myListRepository.getNextSblLikeId());
            session.setAttribute("newJobId", (operationName.equals("Подтягивание разной даты в отчётный файл") ||
                    operationName.equals("Выгрузка телефонов") ||
                    operationName.equals("Импорт телефонов") ||
                    operationName.equals("Выгрузка активностей 2") ||
                    operationName.equals("Выгрузка активностей") ||
                    operationName.equals("Отчет по откликам")) ? myListRepository.getNextSblLikeId() : "");
            this.session = session;

            boolean dataExists = false;
            for (int i = 0; i < data.length; i++) {
                log.trace("saveData start {}", i);

                boolean b = saveData(new String(data[i].getBytes(), StandardCharsets.UTF_8));
                dataExists = dataExists || b;

                log.trace("saveData end {}", i);
            }

            if (!dataExists)
                return "{\"alert\": \"Данных для обработки не найдено.\"}";

            log.trace("ml_explorer start jobId={} newJobId={}", session.getAttribute("jobId"), session.getAttribute("newJobId"));

            myListRepository.mlExplorer(session.getAttribute("jobId").toString(),
                    operationName, userId, campaign, marker, session.getAttribute("newJobId").toString());

            log.trace("ml_explorer end");
            log.trace("findByJobId={}", (session.getAttribute("newJobId") == "") ?
                    session.getAttribute("jobId") : session.getAttribute("newJobId"));

            JSONArray jsonArray = extractData(myListRepository.findByJobId((session.getAttribute("newJobId") == "") ?
                            session.getAttribute("jobId").toString() : session.getAttribute("newJobId").toString(),
                    Sort.by(Sort.Direction.ASC, "createdBy")));

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("alert", "Выполнено успешно.");
            jsonObject.put("data", jsonArray);

            log.trace("executeData end");

            return jsonObject.toString();
        }
        catch (Exception e) {
            return new JSONObject().put("alert", ExceptionUtils.getStackTrace(e)).toString();
        }
    }
}
