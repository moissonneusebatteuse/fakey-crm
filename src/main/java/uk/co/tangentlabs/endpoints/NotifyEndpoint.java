// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) space 
// Source File Name:   NotifyEndpoint.java

package uk.co.tangentlabs.endpoints;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import com.json.JSONObject;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


// Referenced classes of package uk.co.tangentlabs.endpoints:
//            Endpoint

@Component
@Path("/notify/")
public class NotifyEndpoint extends Endpoint
{
	@Autowired
    private SessionFactory sessionFactory;
    
//	@Autowired
//	IEmptyService EmptyService;
    boolean needsToVote;
    
//
//
//    public NotifyEndpoint()
//    {
//        needsToVote = false;
//    }
//
//    @GET
//    @Path("/")
//    public String setVoteRequired()
//    {
//        needsToVote = true;
//        return "{\"result\":\"ok\"}";
//    }
//
//    @POST
//    @Path("/{uuid}/voted/")
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    public String updateVote(@PathParam("uuid") String uuid)
//        throws Exception
//    {
////    	endTransaction(sessionFactory.getCurrentSession());
//        Transaction t = sessionFactory.getCurrentSession().getTransaction();
//        try {
//        	if (t.isActive()) t.rollback();
//        } catch (org.hibernate.TransactionException te){
//        	t = sessionFactory.getCurrentSession().beginTransaction();
//        }
//        Criteria c = sessionFactory.getCurrentSession().createCriteria(uk.co.tangentlabs.entities.Member.class);
//        c.add(Restrictions.like("uuid", uuid));
//        Member m = (Member)c.uniqueResult();
//        if (m == null)
//        {
//            throw new Exception("Device not registered");
//        } else
//        {
//            m.setVoted(true);
//            sessionFactory.getCurrentSession().update(m);
//            sessionFactory.getCurrentSession().flush();
//            t.commit();
//            JSONObject json = new JSONObject(m);
//            return json.toString();
//        }
//    }
//
//    @GET
//    @Path("/{uuid}/")
//    public String getVoteRequired(@PathParam("uuid") String uuid)
//    {
//        ResponseClass response = new ResponseClass();
//        if (!needsToVote)
//        {
//            response.setNotify(false);
//        } else
//        {
//            Transaction t = sessionFactory.getCurrentSession().beginTransaction();
//            try {
//            	if (t.isActive()) t.rollback();
//            	t.begin();
//            } catch (org.hibernate.TransactionException te){
//            	t = sessionFactory.getCurrentSession().beginTransaction();
//            }
//            
//            System.out.print("UUID = "+uuid);
//            Criteria c = sessionFactory.getCurrentSession().createCriteria(uk.co.tangentlabs.entities.Member.class);
//            c.add(Restrictions.like("uuid", uuid));
//            Member m = (Member)c.uniqueResult();
//            if (m == null)
//            {
//                response.setNotify(false);
//                response.setMessage("Device not registered.");
//            } else
//            if (!m.isVoted())
//            {
//                response.setNotify(true);
//                response.setMessage("Please go vote.");
//            } else
//            {
//                response.setNotify(false);
//            }
//            t.commit();
//        }
//        JSONObject array = new JSONObject(response);
//        return array.toString();
//    }
//
//    public class ResponseClass
//    {
//    	public boolean notify;
//        public String message;
//
//        public ResponseClass()
//        {
//        	super();
//            
//        }
//        public boolean getNotify()
//        {
//            return notify;
//        }
//
//        public void setNotify(boolean b)
//        {
//            notify = b;
//        }
//
//        public String getMessage()
//        {
//            return message;
//        }
//
//        public void setMessage(String message)
//        {
//            this.message = message;
//        }
//
//        
//    }
}
