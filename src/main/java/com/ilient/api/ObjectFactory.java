
package com.ilient.api;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ilient.api package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DeleteResponse_QNAME = new QName("http://api.ilient.com/", "deleteResponse");
    private final static QName _Logout_QNAME = new QName("http://api.ilient.com/", "logout");
    private final static QName _SaveResponse_QNAME = new QName("http://api.ilient.com/", "saveResponse");
    private final static QName _GetApiSysAidObjectActions_QNAME = new QName("http://api.ilient.com/", "getApiSysAidObjectActions");
    private final static QName _ExecuteApiSysAidObjectAction_QNAME = new QName("http://api.ilient.com/", "executeApiSysAidObjectAction");
    private final static QName _ExecuteSelectQuery_QNAME = new QName("http://api.ilient.com/", "executeSelectQuery");
    private final static QName _ExecuteSelectQueryResponse_QNAME = new QName("http://api.ilient.com/", "executeSelectQueryResponse");
    private final static QName _Delete_QNAME = new QName("http://api.ilient.com/", "delete");
    private final static QName _Login_QNAME = new QName("http://api.ilient.com/", "login");
    private final static QName _LoadByIdResponse_QNAME = new QName("http://api.ilient.com/", "loadByIdResponse");
    private final static QName _LoadByStringId_QNAME = new QName("http://api.ilient.com/", "loadByStringId");
    private final static QName _LogoutResponse_QNAME = new QName("http://api.ilient.com/", "logoutResponse");
    private final static QName _LoginResponse_QNAME = new QName("http://api.ilient.com/", "loginResponse");
    private final static QName _Save_QNAME = new QName("http://api.ilient.com/", "save");
    private final static QName _LoadById_QNAME = new QName("http://api.ilient.com/", "loadById");
    private final static QName _ExecuteApiSysAidObjectActionResponse_QNAME = new QName("http://api.ilient.com/", "executeApiSysAidObjectActionResponse");
    private final static QName _LoadByStringIdResponse_QNAME = new QName("http://api.ilient.com/", "loadByStringIdResponse");
    private final static QName _GetApiSysAidObjectActionsResponse_QNAME = new QName("http://api.ilient.com/", "getApiSysAidObjectActionsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ilient.api
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ApiTask }
     * 
     */
    public ApiTask createApiTask() {
        return new ApiTask();
    }

    /**
     * Create an instance of {@link ApiTask.CustomFields }
     * 
     */
    public ApiTask.CustomFields createApiTaskCustomFields() {
        return new ApiTask.CustomFields();
    }

    /**
     * Create an instance of {@link ApiTask.CustomDateFields }
     * 
     */
    public ApiTask.CustomDateFields createApiTaskCustomDateFields() {
        return new ApiTask.CustomDateFields();
    }

    /**
     * Create an instance of {@link ApiSysAidUser }
     * 
     */
    public ApiSysAidUser createApiSysAidUser() {
        return new ApiSysAidUser();
    }

    /**
     * Create an instance of {@link ApiSysAidUser.CustomFields }
     * 
     */
    public ApiSysAidUser.CustomFields createApiSysAidUserCustomFields() {
        return new ApiSysAidUser.CustomFields();
    }

    /**
     * Create an instance of {@link ApiSysAidUser.CustomDateFields }
     * 
     */
    public ApiSysAidUser.CustomDateFields createApiSysAidUserCustomDateFields() {
        return new ApiSysAidUser.CustomDateFields();
    }

    /**
     * Create an instance of {@link ApiComputer }
     * 
     */
    public ApiComputer createApiComputer() {
        return new ApiComputer();
    }

    /**
     * Create an instance of {@link ApiComputer.CustomFields }
     * 
     */
    public ApiComputer.CustomFields createApiComputerCustomFields() {
        return new ApiComputer.CustomFields();
    }

    /**
     * Create an instance of {@link ApiComputer.CustomDateFields }
     * 
     */
    public ApiComputer.CustomDateFields createApiComputerCustomDateFields() {
        return new ApiComputer.CustomDateFields();
    }

    /**
     * Create an instance of {@link ApiCompany }
     * 
     */
    public ApiCompany createApiCompany() {
        return new ApiCompany();
    }

    /**
     * Create an instance of {@link ApiCompany.CustomFields }
     * 
     */
    public ApiCompany.CustomFields createApiCompanyCustomFields() {
        return new ApiCompany.CustomFields();
    }

    /**
     * Create an instance of {@link ApiCompany.CustomDateFields }
     * 
     */
    public ApiCompany.CustomDateFields createApiCompanyCustomDateFields() {
        return new ApiCompany.CustomDateFields();
    }

    /**
     * Create an instance of {@link ApiServiceRequest }
     * 
     */
    public ApiServiceRequest createApiServiceRequest() {
        return new ApiServiceRequest();
    }

    /**
     * Create an instance of {@link ApiServiceRequest.CustomFields }
     * 
     */
    public ApiServiceRequest.CustomFields createApiServiceRequestCustomFields() {
        return new ApiServiceRequest.CustomFields();
    }

    /**
     * Create an instance of {@link ApiServiceRequest.CustomDateFields }
     * 
     */
    public ApiServiceRequest.CustomDateFields createApiServiceRequestCustomDateFields() {
        return new ApiServiceRequest.CustomDateFields();
    }

    /**
     * Create an instance of {@link ApiProject }
     * 
     */
    public ApiProject createApiProject() {
        return new ApiProject();
    }

    /**
     * Create an instance of {@link ApiProject.CustomFields }
     * 
     */
    public ApiProject.CustomFields createApiProjectCustomFields() {
        return new ApiProject.CustomFields();
    }

    /**
     * Create an instance of {@link ApiProject.CustomDateFields }
     * 
     */
    public ApiProject.CustomDateFields createApiProjectCustomDateFields() {
        return new ApiProject.CustomDateFields();
    }

    /**
     * Create an instance of {@link ApiCI }
     * 
     */
    public ApiCI createApiCI() {
        return new ApiCI();
    }

    /**
     * Create an instance of {@link ApiCI.CustomFields }
     * 
     */
    public ApiCI.CustomFields createApiCICustomFields() {
        return new ApiCI.CustomFields();
    }

    /**
     * Create an instance of {@link ApiCI.CustomDateFields }
     * 
     */
    public ApiCI.CustomDateFields createApiCICustomDateFields() {
        return new ApiCI.CustomDateFields();
    }

    /**
     * Create an instance of {@link Save }
     * 
     */
    public Save createSave() {
        return new Save();
    }

    /**
     * Create an instance of {@link LoadById }
     * 
     */
    public LoadById createLoadById() {
        return new LoadById();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link ExecuteApiSysAidObjectActionResponse }
     * 
     */
    public ExecuteApiSysAidObjectActionResponse createExecuteApiSysAidObjectActionResponse() {
        return new ExecuteApiSysAidObjectActionResponse();
    }

    /**
     * Create an instance of {@link LoadByStringIdResponse }
     * 
     */
    public LoadByStringIdResponse createLoadByStringIdResponse() {
        return new LoadByStringIdResponse();
    }

    /**
     * Create an instance of {@link GetApiSysAidObjectActionsResponse }
     * 
     */
    public GetApiSysAidObjectActionsResponse createGetApiSysAidObjectActionsResponse() {
        return new GetApiSysAidObjectActionsResponse();
    }

    /**
     * Create an instance of {@link DeleteResponse }
     * 
     */
    public DeleteResponse createDeleteResponse() {
        return new DeleteResponse();
    }

    /**
     * Create an instance of {@link Logout }
     * 
     */
    public Logout createLogout() {
        return new Logout();
    }

    /**
     * Create an instance of {@link ExecuteApiSysAidObjectAction }
     * 
     */
    public ExecuteApiSysAidObjectAction createExecuteApiSysAidObjectAction() {
        return new ExecuteApiSysAidObjectAction();
    }

    /**
     * Create an instance of {@link SaveResponse }
     * 
     */
    public SaveResponse createSaveResponse() {
        return new SaveResponse();
    }

    /**
     * Create an instance of {@link GetApiSysAidObjectActions }
     * 
     */
    public GetApiSysAidObjectActions createGetApiSysAidObjectActions() {
        return new GetApiSysAidObjectActions();
    }

    /**
     * Create an instance of {@link Delete }
     * 
     */
    public Delete createDelete() {
        return new Delete();
    }

    /**
     * Create an instance of {@link ExecuteSelectQueryResponse }
     * 
     */
    public ExecuteSelectQueryResponse createExecuteSelectQueryResponse() {
        return new ExecuteSelectQueryResponse();
    }

    /**
     * Create an instance of {@link ExecuteSelectQuery }
     * 
     */
    public ExecuteSelectQuery createExecuteSelectQuery() {
        return new ExecuteSelectQuery();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link LoadByIdResponse }
     * 
     */
    public LoadByIdResponse createLoadByIdResponse() {
        return new LoadByIdResponse();
    }

    /**
     * Create an instance of {@link LogoutResponse }
     * 
     */
    public LogoutResponse createLogoutResponse() {
        return new LogoutResponse();
    }

    /**
     * Create an instance of {@link LoadByStringId }
     * 
     */
    public LoadByStringId createLoadByStringId() {
        return new LoadByStringId();
    }

    /**
     * Create an instance of {@link ApiServiceRequestActivity }
     * 
     */
    public ApiServiceRequestActivity createApiServiceRequestActivity() {
        return new ApiServiceRequestActivity();
    }

    /**
     * Create an instance of {@link ApiCatalogItem }
     * 
     */
    public ApiCatalogItem createApiCatalogItem() {
        return new ApiCatalogItem();
    }

    /**
     * Create an instance of {@link ApiComputerItemList }
     * 
     */
    public ApiComputerItemList createApiComputerItemList() {
        return new ApiComputerItemList();
    }

    /**
     * Create an instance of {@link ApiComputerItem }
     * 
     */
    public ApiComputerItem createApiComputerItem() {
        return new ApiComputerItem();
    }

    /**
     * Create an instance of {@link ApiSoftware }
     * 
     */
    public ApiSoftware createApiSoftware() {
        return new ApiSoftware();
    }

    /**
     * Create an instance of {@link ApiSupplier }
     * 
     */
    public ApiSupplier createApiSupplier() {
        return new ApiSupplier();
    }

    /**
     * Create an instance of {@link ApiTask.CustomFields.Entry }
     * 
     */
    public ApiTask.CustomFields.Entry createApiTaskCustomFieldsEntry() {
        return new ApiTask.CustomFields.Entry();
    }

    /**
     * Create an instance of {@link ApiTask.CustomDateFields.Entry }
     * 
     */
    public ApiTask.CustomDateFields.Entry createApiTaskCustomDateFieldsEntry() {
        return new ApiTask.CustomDateFields.Entry();
    }

    /**
     * Create an instance of {@link ApiSysAidUser.CustomFields.Entry }
     * 
     */
    public ApiSysAidUser.CustomFields.Entry createApiSysAidUserCustomFieldsEntry() {
        return new ApiSysAidUser.CustomFields.Entry();
    }

    /**
     * Create an instance of {@link ApiSysAidUser.CustomDateFields.Entry }
     * 
     */
    public ApiSysAidUser.CustomDateFields.Entry createApiSysAidUserCustomDateFieldsEntry() {
        return new ApiSysAidUser.CustomDateFields.Entry();
    }

    /**
     * Create an instance of {@link ApiComputer.CustomFields.Entry }
     * 
     */
    public ApiComputer.CustomFields.Entry createApiComputerCustomFieldsEntry() {
        return new ApiComputer.CustomFields.Entry();
    }

    /**
     * Create an instance of {@link ApiComputer.CustomDateFields.Entry }
     * 
     */
    public ApiComputer.CustomDateFields.Entry createApiComputerCustomDateFieldsEntry() {
        return new ApiComputer.CustomDateFields.Entry();
    }

    /**
     * Create an instance of {@link ApiCompany.CustomFields.Entry }
     * 
     */
    public ApiCompany.CustomFields.Entry createApiCompanyCustomFieldsEntry() {
        return new ApiCompany.CustomFields.Entry();
    }

    /**
     * Create an instance of {@link ApiCompany.CustomDateFields.Entry }
     * 
     */
    public ApiCompany.CustomDateFields.Entry createApiCompanyCustomDateFieldsEntry() {
        return new ApiCompany.CustomDateFields.Entry();
    }

    /**
     * Create an instance of {@link ApiServiceRequest.CustomFields.Entry }
     * 
     */
    public ApiServiceRequest.CustomFields.Entry createApiServiceRequestCustomFieldsEntry() {
        return new ApiServiceRequest.CustomFields.Entry();
    }

    /**
     * Create an instance of {@link ApiServiceRequest.CustomDateFields.Entry }
     * 
     */
    public ApiServiceRequest.CustomDateFields.Entry createApiServiceRequestCustomDateFieldsEntry() {
        return new ApiServiceRequest.CustomDateFields.Entry();
    }

    /**
     * Create an instance of {@link ApiProject.CustomFields.Entry }
     * 
     */
    public ApiProject.CustomFields.Entry createApiProjectCustomFieldsEntry() {
        return new ApiProject.CustomFields.Entry();
    }

    /**
     * Create an instance of {@link ApiProject.CustomDateFields.Entry }
     * 
     */
    public ApiProject.CustomDateFields.Entry createApiProjectCustomDateFieldsEntry() {
        return new ApiProject.CustomDateFields.Entry();
    }

    /**
     * Create an instance of {@link ApiCI.CustomFields.Entry }
     * 
     */
    public ApiCI.CustomFields.Entry createApiCICustomFieldsEntry() {
        return new ApiCI.CustomFields.Entry();
    }

    /**
     * Create an instance of {@link ApiCI.CustomDateFields.Entry }
     * 
     */
    public ApiCI.CustomDateFields.Entry createApiCICustomDateFieldsEntry() {
        return new ApiCI.CustomDateFields.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ilient.com/", name = "deleteResponse")
    public JAXBElement<DeleteResponse> createDeleteResponse(DeleteResponse value) {
        return new JAXBElement<DeleteResponse>(_DeleteResponse_QNAME, DeleteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Logout }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ilient.com/", name = "logout")
    public JAXBElement<Logout> createLogout(Logout value) {
        return new JAXBElement<Logout>(_Logout_QNAME, Logout.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ilient.com/", name = "saveResponse")
    public JAXBElement<SaveResponse> createSaveResponse(SaveResponse value) {
        return new JAXBElement<SaveResponse>(_SaveResponse_QNAME, SaveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetApiSysAidObjectActions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ilient.com/", name = "getApiSysAidObjectActions")
    public JAXBElement<GetApiSysAidObjectActions> createGetApiSysAidObjectActions(GetApiSysAidObjectActions value) {
        return new JAXBElement<GetApiSysAidObjectActions>(_GetApiSysAidObjectActions_QNAME, GetApiSysAidObjectActions.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExecuteApiSysAidObjectAction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ilient.com/", name = "executeApiSysAidObjectAction")
    public JAXBElement<ExecuteApiSysAidObjectAction> createExecuteApiSysAidObjectAction(ExecuteApiSysAidObjectAction value) {
        return new JAXBElement<ExecuteApiSysAidObjectAction>(_ExecuteApiSysAidObjectAction_QNAME, ExecuteApiSysAidObjectAction.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExecuteSelectQuery }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ilient.com/", name = "executeSelectQuery")
    public JAXBElement<ExecuteSelectQuery> createExecuteSelectQuery(ExecuteSelectQuery value) {
        return new JAXBElement<ExecuteSelectQuery>(_ExecuteSelectQuery_QNAME, ExecuteSelectQuery.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExecuteSelectQueryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ilient.com/", name = "executeSelectQueryResponse")
    public JAXBElement<ExecuteSelectQueryResponse> createExecuteSelectQueryResponse(ExecuteSelectQueryResponse value) {
        return new JAXBElement<ExecuteSelectQueryResponse>(_ExecuteSelectQueryResponse_QNAME, ExecuteSelectQueryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Delete }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ilient.com/", name = "delete")
    public JAXBElement<Delete> createDelete(Delete value) {
        return new JAXBElement<Delete>(_Delete_QNAME, Delete.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ilient.com/", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoadByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ilient.com/", name = "loadByIdResponse")
    public JAXBElement<LoadByIdResponse> createLoadByIdResponse(LoadByIdResponse value) {
        return new JAXBElement<LoadByIdResponse>(_LoadByIdResponse_QNAME, LoadByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoadByStringId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ilient.com/", name = "loadByStringId")
    public JAXBElement<LoadByStringId> createLoadByStringId(LoadByStringId value) {
        return new JAXBElement<LoadByStringId>(_LoadByStringId_QNAME, LoadByStringId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogoutResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ilient.com/", name = "logoutResponse")
    public JAXBElement<LogoutResponse> createLogoutResponse(LogoutResponse value) {
        return new JAXBElement<LogoutResponse>(_LogoutResponse_QNAME, LogoutResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ilient.com/", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Save }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ilient.com/", name = "save")
    public JAXBElement<Save> createSave(Save value) {
        return new JAXBElement<Save>(_Save_QNAME, Save.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoadById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ilient.com/", name = "loadById")
    public JAXBElement<LoadById> createLoadById(LoadById value) {
        return new JAXBElement<LoadById>(_LoadById_QNAME, LoadById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExecuteApiSysAidObjectActionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ilient.com/", name = "executeApiSysAidObjectActionResponse")
    public JAXBElement<ExecuteApiSysAidObjectActionResponse> createExecuteApiSysAidObjectActionResponse(ExecuteApiSysAidObjectActionResponse value) {
        return new JAXBElement<ExecuteApiSysAidObjectActionResponse>(_ExecuteApiSysAidObjectActionResponse_QNAME, ExecuteApiSysAidObjectActionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoadByStringIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ilient.com/", name = "loadByStringIdResponse")
    public JAXBElement<LoadByStringIdResponse> createLoadByStringIdResponse(LoadByStringIdResponse value) {
        return new JAXBElement<LoadByStringIdResponse>(_LoadByStringIdResponse_QNAME, LoadByStringIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetApiSysAidObjectActionsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ilient.com/", name = "getApiSysAidObjectActionsResponse")
    public JAXBElement<GetApiSysAidObjectActionsResponse> createGetApiSysAidObjectActionsResponse(GetApiSysAidObjectActionsResponse value) {
        return new JAXBElement<GetApiSysAidObjectActionsResponse>(_GetApiSysAidObjectActionsResponse_QNAME, GetApiSysAidObjectActionsResponse.class, null, value);
    }

}
