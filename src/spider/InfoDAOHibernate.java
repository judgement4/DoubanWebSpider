package spider;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class InfoDAOHibernate implements Dao{

	@Override
	public void save(InfoModel model1) {
		// TODO Auto-generated method stub
		InfoModel model=new InfoModel();
		model.setSns("in");
		model.setIfshared("zhuan");
		model.setSource("APP");
		model.setUpdateTime("2342");
		System.out.println("save");
		Session s = HibernateSessionFactory.getSession();
		if(s==null){
			System.out.println("null");
		}else{
			System.out.println("save");
		}
		Transaction ts = s.beginTransaction();
		s.save(model);
		
		try{
		ts.commit();
		}catch(Exception e){
			ts.rollback();
			System.out.println("save");
		}finally{
			HibernateSessionFactory.closeSession();
		}
		
	}

}
