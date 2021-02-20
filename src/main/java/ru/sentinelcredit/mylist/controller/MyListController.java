package ru.sentinelcredit.mylist.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import ru.sentinelcredit.mylist.repository.InfoRepository;
import ru.sentinelcredit.mylist.repository.OperationRepository;
import ru.sentinelcredit.mylist.service.MyListService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class MyListController {
    @Value("${ui.row.part-size}")
    private Integer rowPartSize;
    @Value("${ui.row.max-size}")
    private Integer rowMaxSize;
    @Value("${ui.col.max-size}")
    private Integer colMaxSize;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private MyListService myListService;
    @Autowired
    private InfoRepository infoRepository;

    @GetMapping("/mylist/ui")
    public String getOperation(@RequestParam(value = "login", required = true) String login,
                           Model model) {

        log.trace("getOperation start login={}", login);

        model.addAttribute("login", login);
        model.addAttribute("operation", operationRepository.findByCreatorLogin(login));
        model.addAttribute("info", infoRepository.findAll());
        model.addAttribute("rowPartSize", rowPartSize);
        model.addAttribute("rowMaxSize", rowMaxSize);
        model.addAttribute("colMaxSize", colMaxSize);

        log.trace("getOperation end");

        return "ui";
    }

    @ResponseBody
    @PostMapping(value = "/mylist/execute", produces = "application/json")
    public String execOperation(@RequestParam(value = "login", required = true) String login,
                           @RequestParam(value="operation", required = true) String operationName,
                           @RequestParam(value = "data", required = true) MultipartFile[] data,
                           @RequestParam(value = "marker", required = true) String marker,
                           @RequestParam(value = "campaign", required = true) String campaign,
                           Model model) {

        if (login.length() == 0 || operationName.length() == 0 || data.length == 0)
            return "{\"alert\": \"Обязательные параметры не заполнены.\"}";

        return myListService.executeData(login, operationName, data, marker, campaign);
    }
}
