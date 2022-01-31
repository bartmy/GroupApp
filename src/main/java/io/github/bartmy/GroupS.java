package io.github.bartmy;

import io.github.bartmy.App.LandingPage.Login;
import io.github.bartmy.App.ToDo.HibernateUtil;
import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.component.AbstractLifeCycle;
import org.eclipse.jetty.util.component.LifeCycle;
import org.eclipse.jetty.webapp.*;

public class GroupS {
    public static void main(String[] args) throws Exception {

        var webapp = new WebAppContext();
        webapp.setResourceBase("src/main/webapp");
        webapp.setContextPath("/");
        webapp.setConfigurations(new Configuration[]{
                new AnnotationConfiguration(),
                new WebInfConfiguration(),
                new WebXmlConfiguration(),
                new MetaInfConfiguration(),
                new FragmentConfiguration(),
                new EnvConfiguration(),
                new PlusConfiguration(),
                new JettyWebXmlConfiguration(),
                new WebAppConfiguration()
        });
//        webapp.setInitParameter("org.eclipse.jetty.servlet.Default.maxCachedFiles", "0");
        webapp.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/classes/.*");
//        webapp.addServlet(io.github.bartmy.App.App.HelloServlet.class, "/api/*");
        var server = new Server(8080);
        server.setHandler(webapp);

        server.addEventListener(new AbstractLifeCycle.AbstractLifeCycleListener() {
            @Override
            public void lifeCycleStopped(LifeCycle event) {
                HibernateUtil.close();
            }
        });
        server.start();
        server.join();

//        io.github.bartmy.App.App appStart = new io.github.bartmy.App.App();
//        while (io.github.bartmy.App.App.isProgramOn()){
//            appStart.startProgram();
//        }

//        Login login = new Login();
//        login.testLogin("bartekm", "haslo");

    }
}
