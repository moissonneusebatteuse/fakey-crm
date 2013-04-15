// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) space 
// Source File Name:   HtmlEndpoint.java

package uk.co.tangentlabs.endpoints;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.springframework.stereotype.Component;
import uk.co.tangentlabs.util.Utils;

// Referenced classes of package uk.co.tangentlabs.endpoints:
//            Endpoint
@Component
@Path("/run.html")
public class HtmlEndpoint extends Endpoint
{

    public HtmlEndpoint()
    {
    }

    @GET
    public String basic()
        throws IOException
    {
        java.io.InputStream input = getClass().getResourceAsStream("/run.html");
        return Utils.getStringFromInputStream(input);
    }
}
