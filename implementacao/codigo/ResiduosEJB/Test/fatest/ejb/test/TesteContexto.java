package fatest.ejb.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import junit.framework.TestCase;
import fatesg.projeto.integrador.dao.AbstractDAO;

public class TesteContexto extends TestCase {

	@SuppressWarnings("rawtypes")
	public void testCriarContexto() throws IOException {

		Properties p =new Properties();
		p.load(this.getClass().getResourceAsStream("jndi.properties"));
		
		try {
			Context ctx = new InitialContext(p);
			AbstractDAO dao = (AbstractDAO) ctx.lookup("dao/remote");
			assertNotNull(dao);
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}
}
