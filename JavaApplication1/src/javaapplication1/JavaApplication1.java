/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import entity.Gruppyi;
import entity.Studentyi;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author 19613
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = sf.openSession();

        Transaction t1 = s.beginTransaction();
        List<Studentyi> q = s.createQuery("from Studentyi ").list();

        for (Studentyi u : q) {
            System.out.println(u.getFamiliya());
            //u.setStatusDate(new Date);
            s.saveOrUpdate(u);
        }
        s.flush();
        t1.commit();

        Transaction t2 = s.beginTransaction();

        Query grQuery = s.createQuery("from Gruppyi g where g.nazvanie = :name");
        grQuery.setParameter("name", "It-1");
        List<Gruppyi> l= grQuery.list();
         for (Gruppyi g : l) {
            System.out.println(g.getStatus());
            
            s.saveOrUpdate(g);
        }
        s.close();
    }
}
