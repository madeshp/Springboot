package com.springboot.learning.constants;

public interface EmployeeConstants {


    // ####### Error Codes #####################
    public static final String ERROR_CODE_INVALID_SERVICE_REQUEST = "1000";
    public static final String ERROR_CODE_INTERNAL = "9999";
    public static final String ERROR_CODE_EMPLOYEE_NOT_FOUND = "1001";

    // ####### Error Messages #####################
    public static final String ERROR_REASON_INVALID_SERVICE_REQUEST = "Invalid Service Request";
    public static final String ERROR_REASON_EMPLOYEE_NOT_FOUND = "Employee not found";
    public static final String SUCCESS_STRING = "Success";
    public static final String FAULT_STRING = "Internal Error";
    public static final String DEFAULT_FAULT_ACTOR = "PROVIDER";
    public static final String DEFAULT_ERROR_STRING = "Error Occured - ";
/*    public static final String SALESFORCE_DATA_ERROR_MSG = "No records found for CheckSpecialHandling criteria";
    public static final String SALESFORCE_CONNECTION_ERROR_MSG = "SalesforceConnectionError";
    public static final String SALESFORCE_LOGIN_ERROR_MSG = "SalesforceExecuteSOAPError";
    public static final String SALESFORCE_EXECUTION_ERROR_MSG = "SalesforceLoginError";*/

    // ####### Controller Constants #####################
  /*  public static final String RESTENDPOINT_URI = "/checkSpecialHandling";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final int SUCCESS_CODE = 200;
    public static final int ERROR_CODE = 500;

    // ####### Endpoint Constants #####################
    public static final String OPERATION_NAME = "CheckSpecialHandling";
    public static final String JH_HEADER_NS = "http://www.esb.manulife.com/xsd/common/jh/header";
    public static final String NAMESPACE_URI = "http://www.esb.manulife.com/xsd/Insurance/jh/SFDCPolicyManagement";
    public static final String JHHEADER_INVALID = "JHHeader Invalid";

    // ####### Request Constants #####################
    public static final String POL_NUMBER = " Policy_Contract__r.Name= '";
    public static final String SUB_DIVISION_QUALIFIER = " and Policy_Contract__r.PLC_Code__c= '";
    public static final String SPECIAL_HANDLING_TYPE = " and LFOPS_Type__c= '";
    public static final String END_QUOTE = "'";

    // ####### Salesforce Constants #####################
    public static final String START_QUERY = "SELECT LastModifiedDate, Active__c FROM Special_Handling__c WHERE";
    public static final String RECORDS = "records";
    public static final String ACTIVE_C = "Active__c";
    public static final String INDICATOR_TRUE = "true";*/
}
