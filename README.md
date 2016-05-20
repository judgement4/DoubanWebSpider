# DoubanWebSpider
基于WebMagic的一个JAVA爬虫，抓取豆瓣特定用户的广播中的SNS信息


----------


使用工具
* WebMagic 0.5.2
* Hibernate 3.3.2 GA
* mysql-connector-java-3.1.14

抓取的页面类似于：

> https://www.douban.com/people/XXXXXXXX/statuses?p=XX

抽取了以下几个数据：
* sns：广播的内容
* updateTime：更新的时间
* source：来源WEB还是APP
* ifshared：转播还是原创

使用了普通的JDBC和Hibernate两种方式持久化到数据库。
即，InfoDAO和InfoDAOHibernate。
以上两个类都继承了Dao这个接口，并重写了save方法。

