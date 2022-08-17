# mylist

We are now talking about working with Oracle Siebel CRM. In everyday operational activities, it is often necessary to process a relatively large set of data. For example, in the CC, specialists of automated operations are sent a list of phones that need to be reached today. The system is required through the web interface to allow you to enter several rows of data, preferably at the touch of a button, check the data, perform an action on the downloaded data set and get the result of the action.
  
As you know, in Siebel, the base sheet applet supports manual row-by-row insertion of records. I.e., the user needs to click and enter several records line by line, what if there are several thousand records?
You can configure the import. To enable the import, the developer's participation is required and revision will be required.

The utility offers an opportunity to cope with the problem of mass data loading. Data can be uploaded to the CRM system via the clipboard of the operating system, copy paste from excel is available. Before downloading, you can visually view the data. Verification, data processing and the formation of the result occurs with one button. The user has several data processing operations available, after entering the data, he chooses the processing method himself. 

The utility is implemented as a Spring Boot microservice. the web-based interface is implemented using a spreadsheet [x-spreadsheet](https://github.com/myliang/x-spreadsheet ). Embedded in Oracle Siebel CRM using an iframe.
