package edu.ecnu.ica.kgpro.vocabulary;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.log4j.pattern.NameAbbreviator;

public class INDUSTRY {
	/** namespace for INDUSTRY vocabularies
	 */
	public static final String uri = "";
	
	/** returns the URI for this schema
     * @return the URI for this schema
     */
    public static String getURI() {
          return uri;
    }

    private static final Model m = ModelFactory.createDefaultModel();
    
    public static final Resource COMPANYID = m.createResource(uri + "COMPANYID");
    public static final Resource ARTIFICIALPERSON = m.createResource(uri + "ARTIFICIALPERSON");
}
