package rd.huma.dashboard.servicios.integracion.activedirectory;


import java.util.Properties;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;

/**
 * Query Active Directory using Java
 *
 * @filename ActiveDirectory.java
 * @author <a href="mailto:jeeva@myjeeva.com">Jeevanandam Madanagopal</a>
 * @copyright &copy; 2010-2012 www.myjeeva.com
 */
public class ServicioActiveDirectory {
	// Logger
	private static final Logger LOG = Logger.getLogger(ServicioActiveDirectory.class.getName());

    //required private variables
    private Properties properties;
    private DirContext dirContext;
    private SearchControls searchCtls;
	private String[] returnAttributes = { "sAMAccountName", "givenName", "cn", "mail" };
    private String domainBase;
    private String baseFilter = "(&((&(objectCategory=Person)(objectClass=User)))";

	private String usuario;

	private SearchResult resultado;

    /**
     * constructor with parameter for initializing a LDAP context
     *
     * @param usuario a {@link java.lang.String} object - username to establish a LDAP connection
     * @param password a {@link java.lang.String} object - password to establish a LDAP connection
     * @param domainController a {@link java.lang.String} object - domain controller name for LDAP connection
     */
    public ServicioActiveDirectory(EntConfiguracionGeneral configuracionGeneral, String usuario, String password) {
    	this.usuario = usuario;

    	String domainController =  configuracionGeneral.getNombreActiveDirectory();

        properties = new Properties();

        properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        properties.put(Context.PROVIDER_URL, "LDAP://"+domainController);
        properties.put(Context.SECURITY_PRINCIPAL, "sigefint\\"+ usuario); //domainController);
        properties.put(Context.SECURITY_CREDENTIALS, password);
        properties.put(Context.REFERRAL, "ignore");

        //initializing active directory LDAP connection
        try {
			dirContext = new InitialDirContext(properties);
		} catch (NamingException e) {
			LOG.severe(e.getMessage());
		}

        //default domain base for search
        domainBase = configuracionGeneral.getDomainBaseActiveDirecty();

        //initializing search controls
        searchCtls = new SearchControls();
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        searchCtls.setReturningAttributes(returnAttributes);
    }

    /**
     * search the Active directory by username/email id for given search base
     *
     * @param searchValue a {@link java.lang.String} object - search value used for AD search for eg. username or email
     * @param searchBy a {@link java.lang.String} object - scope of search by username or by email id
     * @param searchBase a {@link java.lang.String} object - search base value for scope tree for eg. DC=myjeeva,DC=com
     * @return search result a {@link javax.naming.NamingEnumeration} object - active directory search result
     * @throws NamingException
     */
    public boolean isValido()  {
    	try {
    		NamingEnumeration<SearchResult> resultados = dirContext.search(domainBase,  baseFilter+ "(mail=" + usuario+ "@sigef.gov.do))", this.searchCtls);
    		if (resultados.hasMore()){
    			this.resultado = resultados.next();
    			return true;
    		}

    	}catch(Exception e){
    	}
    	return false;
    }

    /**
     * closes the LDAP connection with Domain controller
     */
    public void closeLdapConnection(){
        try {
            if(dirContext != null)
                dirContext.close();
        }
        catch (NamingException e) {
        	LOG.severe(e.getMessage());
        }
    }

    public SearchResult getResultado() {
		return resultado;
	}
}