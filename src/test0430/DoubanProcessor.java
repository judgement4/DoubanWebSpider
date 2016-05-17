package test0430;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class DoubanProcessor implements PageProcessor{
	
	private Site site=Site.me()
						   .setCharset("utf-8")
						   .setUserAgent(str)
						   .setRetryTimes(4)
						   .setSleepTime(2000)
						   .setDomain("douban.com")
						   .addCookie("dbcl2", "53827874:lginbDA/+9w");
	public static String str="Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1="https://www.douban.com/people/53827874/statuses";
		String str2="https://www.douban.com/group/topic/6457749/";
		String str3="https://www.douban.com/people/72066296/statuses";
		Spider.create(new DoubanProcessor())
		.addUrl(str1)
		.thread(2)
		.addPipeline(new FilePipeline("/workspace/WebMagic"))
		.start();

	}

	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		
//		page.addTargetRequests(page.getHtml().links()
//				.regex("http://www\\.douban\\.com/people/53827874/statuses\\?p=\\d").all());
		page.addTargetRequests(page.getHtml().css("div.paginator").links().regex(".*\\?p=\\d{1,2}").all());

//		page.putField("say", page.getHtml().css("div.status-saying").css("p").all().toString());
		

		List<String> StatusSaying = page.getHtml().xpath("//div[@data-action='1']").xpath("//div[@class='status-saying']/blockquote/p/text()").all();
		List<String> actions = page.getHtml().xpath("//div[@data-action='1' and @data-target-type='sns']").xpath("div[@class='actions']").all();
		List<String> time = page.getHtml().xpath("//div[@data-action='1' and @data-target-type='sns']").xpath("div[@class='actions']").regex("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}").all();
		List<InfoModel> infoList=new ArrayList<InfoModel>();
		
		if(StatusSaying.isEmpty()){
			page.setSkip(true);
		}		
		
		for(int i=0; i<actions.size(); i++){
			InfoModel info=new InfoModel();
			
			info.setSns(StatusSaying.get(i).toString());
			info.setUpdateTime(time.get(i).toString());
			
			if(actions.get(i).toString().contains("status_source")){
				info.setSource("¶¹°êAPP");
			}else
				info.setSource("WEB");
			infoList.add(info);
		}
		page.putField("broadcast", infoList);
		
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return this.site;
	}

}
