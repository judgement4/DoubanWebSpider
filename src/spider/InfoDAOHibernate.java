package spider;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class InfoDAOHibernate implements Dao{

	@Override
	public void save(InfoModel model) {
		// TODO Auto-generated method stub
//		InfoModel model=new InfoModel();
/*		model.setSns("in");
		model.setIfshared("zhuan");
		model.setSource("APP");
		model.setUpdateTime("2342");
		System.out.println("save");*/
		Session s = HibernateSessionFactory.getSession();
		Transaction ts = s.beginTransaction();
		
		
		try{
			s.save(model);
			ts.commit();
		}catch(Exception e){
			ts.rollback();
			System.out.println("save error");
		}finally{
			s.flush();
			HibernateSessionFactory.closeSession();
		}
		
	}

}
