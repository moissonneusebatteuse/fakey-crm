// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) space 
// Source File Name:   ServletContainer.java

package uk.co.tangentlabs.core;

import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.spi.container.WebApplication;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.ApplicationContext;

public class ServletContainer extends SpringServlet
{

    public ServletContainer()
    {
    }

    public void destroy()
    {
    }

    protected void initiate(ResourceConfig rc, WebApplication wa)
    {
        super.initiate(rc, wa);
        ctx = getContext();
    }

    public static ApplicationContext getApplicationContext()
    {
        return ctx;
    }
    
    public static String getEnvironmentVariable(String key)
    {
        return System.getenv(key);
    }

    private static final long serialVersionUID = 1L;
    private static ApplicationContext ctx = null;

}
