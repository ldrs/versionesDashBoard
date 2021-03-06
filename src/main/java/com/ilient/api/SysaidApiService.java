
package com.ilient.api;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "SysaidApiService", targetNamespace = "http://api.ilient.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface SysaidApiService {


    /**
     * 
     * @param apiSysObj
     * @param sessionId
     * @return
     *     returns java.lang.Object
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "save", targetNamespace = "http://api.ilient.com/", className = "com.ilient.api.Save")
    @ResponseWrapper(localName = "saveResponse", targetNamespace = "http://api.ilient.com/", className = "com.ilient.api.SaveResponse")
    public Object save(
        @WebParam(name = "sessionId", targetNamespace = "")
        long sessionId,
        @WebParam(name = "apiSysObj", targetNamespace = "")
        ApiSysaidObject apiSysObj);

    /**
     * 
     * @param apiSysObj
     * @param sessionId
     */
    @WebMethod
    @RequestWrapper(localName = "delete", targetNamespace = "http://api.ilient.com/", className = "com.ilient.api.Delete")
    @ResponseWrapper(localName = "deleteResponse", targetNamespace = "http://api.ilient.com/", className = "com.ilient.api.DeleteResponse")
    public void delete(
        @WebParam(name = "sessionId", targetNamespace = "")
        long sessionId,
        @WebParam(name = "apiSysObj", targetNamespace = "")
        ApiSysaidObject apiSysObj);

    /**
     * 
     * @param accountId
     * @param userName
     * @param password
     * @return
     *     returns long
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "login", targetNamespace = "http://api.ilient.com/", className = "com.ilient.api.Login")
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://api.ilient.com/", className = "com.ilient.api.LoginResponse")
    public long login(
        @WebParam(name = "accountId", targetNamespace = "")
        String accountId,
        @WebParam(name = "userName", targetNamespace = "")
        String userName,
        @WebParam(name = "password", targetNamespace = "")
        String password);

    /**
     * 
     * @param sessionId
     */
    @WebMethod
    @RequestWrapper(localName = "logout", targetNamespace = "http://api.ilient.com/", className = "com.ilient.api.Logout")
    @ResponseWrapper(localName = "logoutResponse", targetNamespace = "http://api.ilient.com/", className = "com.ilient.api.LogoutResponse")
    public void logout(
        @WebParam(name = "sessionId", targetNamespace = "")
        long sessionId);

    /**
     * 
     * @param id
     * @param apiSysObj
     * @param sessionId
     * @return
     *     returns com.ilient.api.ApiSysaidObject
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "loadById", targetNamespace = "http://api.ilient.com/", className = "com.ilient.api.LoadById")
    @ResponseWrapper(localName = "loadByIdResponse", targetNamespace = "http://api.ilient.com/", className = "com.ilient.api.LoadByIdResponse")
    public ApiSysaidObject loadById(
        @WebParam(name = "sessionId", targetNamespace = "")
        long sessionId,
        @WebParam(name = "apiSysObj", targetNamespace = "")
        ApiSysaidObject apiSysObj,
        @WebParam(name = "id", targetNamespace = "")
        Object id);

    /**
     * 
     * @param apiSysObj
     * @param actionParams
     * @param sessionId
     * @param actionName
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "executeApiSysAidObjectAction", targetNamespace = "http://api.ilient.com/", className = "com.ilient.api.ExecuteApiSysAidObjectAction")
    @ResponseWrapper(localName = "executeApiSysAidObjectActionResponse", targetNamespace = "http://api.ilient.com/", className = "com.ilient.api.ExecuteApiSysAidObjectActionResponse")
    public String executeApiSysAidObjectAction(
        @WebParam(name = "sessionId", targetNamespace = "")
        long sessionId,
        @WebParam(name = "apiSysObj", targetNamespace = "")
        ApiSysaidObject apiSysObj,
        @WebParam(name = "actionName", targetNamespace = "")
        String actionName,
        @WebParam(name = "actionParams", targetNamespace = "")
        List<Object> actionParams);

    /**
     * 
     * @param apiSysObj
     * @param condition
     * @param sessionId
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "executeSelectQuery", targetNamespace = "http://api.ilient.com/", className = "com.ilient.api.ExecuteSelectQuery")
    @ResponseWrapper(localName = "executeSelectQueryResponse", targetNamespace = "http://api.ilient.com/", className = "com.ilient.api.ExecuteSelectQueryResponse")
    public List<String> executeSelectQuery(
        @WebParam(name = "sessionId", targetNamespace = "")
        long sessionId,
        @WebParam(name = "apiSysObj", targetNamespace = "")
        ApiSysaidObject apiSysObj,
        @WebParam(name = "condition", targetNamespace = "")
        String condition);

    /**
     * 
     * @param apiSysObj
     * @param sessionId
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getApiSysAidObjectActions", targetNamespace = "http://api.ilient.com/", className = "com.ilient.api.GetApiSysAidObjectActions")
    @ResponseWrapper(localName = "getApiSysAidObjectActionsResponse", targetNamespace = "http://api.ilient.com/", className = "com.ilient.api.GetApiSysAidObjectActionsResponse")
    public String getApiSysAidObjectActions(
        @WebParam(name = "sessionId", targetNamespace = "")
        long sessionId,
        @WebParam(name = "apiSysObj", targetNamespace = "")
        ApiSysaidObject apiSysObj);

    /**
     * 
     * @param id
     * @param apiSysObj
     * @param sessionId
     * @return
     *     returns com.ilient.api.ApiSysaidObject
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "loadByStringId", targetNamespace = "http://api.ilient.com/", className = "com.ilient.api.LoadByStringId")
    @ResponseWrapper(localName = "loadByStringIdResponse", targetNamespace = "http://api.ilient.com/", className = "com.ilient.api.LoadByStringIdResponse")
    public ApiSysaidObject loadByStringId(
        @WebParam(name = "sessionId", targetNamespace = "")
        long sessionId,
        @WebParam(name = "apiSysObj", targetNamespace = "")
        ApiSysaidObject apiSysObj,
        @WebParam(name = "id", targetNamespace = "")
        String id);

}
