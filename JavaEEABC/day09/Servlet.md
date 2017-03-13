#Servlet

   
##HTTP协议
HyperText transform protocal

###Request

组成：
	**请求行  请求头    请求体**

1.请求行：第一行

	格式：请求方式  访问的资源   协议/版本
	例如:GET /day0801/1.html HTTP/1.1


	请求方式：
	get请求和post请求
		get请求没有请求体，会把参数拼接到url后面，参数大小有限制
		post请求有请求体，会把请求参数放在请求体中，相对较为安全，参数长度没有限制。

2.请求头：第二行到空格结束

	格式：key/value (value可以是多个值)
	
	常见的请求头：
			Accept: text/html,image/bmp		--支持数据类型    text/		html text/css text/javascript 大类型/小类型 mime类型
			Accept-Charset: ISO-8859-1	--字符集
			Accept-Encoding: gzip		--支持压缩
			Accept-Language:zh-cn 		--语言环境
			Host: www.itcast.cn:80		--访问主机
			If-Modified-Since: Tue, 11 Jul 2000 18:23:51 GMT	  --缓存文件的最后修改时间
			Referer: http://www.itcast.com/index.jsp	 --来自哪个页面、防盗链
			User-Agent: Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0)
			Cookie
			Connection:Keep-Alive   	--链接状态	
 **需要掌握的头信息**
	
Referer ：防盗链

User-Agent： 浏览器类型

If-Modified-Since：修改时间

3.请求体：空行一下的内容

		只有post请求才有请求体   get请求参数和拼在URL后面

###Response

组成：
	**响应行  响应头    响应体**

1.响应行：
		
	格式：协议/版本 状态码 状态码说明
	例如:HTTP/1.1 200 OK
		状态码:
			200 正常响应成功
			302 重定向
			304 读缓存
			404 用户操作资源不存在
			500 服务器内部异常

2.响应头:从响应信息的第二行到空行结束


	格式:key/value(value可以是多个值)
		常见的头
			Location: http://www.it315.org/index.jsp 	--跳转方向 和302一起使用的
			Server:apache tomcat			--服务器型号
			Content-Encoding: gzip 			--数据压缩
			Content-Length: 80 			--数据长度
			Content-Language: zh-cn 		--语言环境
			Content-Type: text/html; charset=GB2312 		--数据类型
			Last-Modified: Tue, 11 Jul 2000 18:23:51 GMT	--最后修改时间
			Refresh: 1;url=http://www.it315.org		--定时刷新
			Content-Disposition: attachment; filename=aaa.zip	--下载
			Set-Cookie:SS=Q0=5Lb_nQ; path=/search
			Expires: -1					--缓存
			Cache-Control: no-cache  			--缓存
			Pragma: no-cache   				--缓存
			Connection: Keep-Alive   			--连接
	
 **需要掌握的头信息**	

Content-Type     网页的--数据类型

 Location     一般与与302结合  跳转方向

 Last-Modified   网页文件最后修改时间

Refresh    --定时刷新

Content-Disposition   --下载

Set-Cookie


##Servlet

定义：动态的web开发技术，本质是一个类，运行在服务器端的一个java小程序。用于处理业务逻辑，生成动态web内容。

###编写一个servlert的步骤：

		1.编写一个类
			a.继承HttpServlet
			b.重写doGet或者doPost方法
		2.编写配置文件(web-info/web.xml)
			a.注册servlet
			b.绑定路径
		3.访问：
			http://主机:端口号/项目名/路径
		
如何接受提交的参数:

```
String value = request.getParameter("key");
回写内容：
response
response.getWriter().print("内容");
//处理乱码  在最前面 写上：
resp.setContentType("text/html;charset=utf-8");
```


### Servlet常用方法

```java
    void init(ServletConfig config); 进行初始化操作
    void service(ServletRequest request,ServletResponse response); 服务 处理业务逻辑
    void destroy():销毁
    ServletConfig getServletConfig();获取配置信息的
```



    
#### Servlet的生命周期 
serlvet是单实例多线程
默认第一次访问的时候，服务器创建Servlet，并且调用init实现初始化操作，并调用一次service方法，每当请求来的时候，服务器创建一个线程，调用service方法来执行自己的业务。

1.init():服务器第一次访问的时候执行   只执行一次
2.service(): 请求一次执行一次。
3.destroy():只执行一次 当servlet被移除或者服务器正常关闭的时候。


### url-pattern的配置


    1:完全匹配  必须以"/"开始 例如: /hello /a/b/c
    2:目录匹配  必须"/"开始  以"*"结束   例如: /a/*  /*
    3:后缀名匹配 以"*"开始 以字符结尾 例如: *.jsp  *.do  *.action

优先级:
		完全匹配>目录匹配>后缀名匹配
		
	
### load-on-startup

是在servlet中的一个标签，作用：用来修改servlet的初始化时机。
取值： 正整数  **值越大优先级越低**

###协议路径的写法

相对路径:
		当前路径    ./ 或者 什么都不写
		上一级路径 ../
	绝对路径:(我们使用)
		带主机和协议的绝对路径(访问站外资源)
			http://www.itheima.com/xxxx
			http://localhost:80/day09/hello
		不带主机和协议的绝对路径
			/day09/hello(经常使用)

### 常见的响应头
案例：登录失败,提示"用户名密码不匹配",3秒以后跳转到登录页面

分析：定时刷新
常见的响应头-refresh
响应头格式:
		refresh:秒数;url=跳转的路径
设置响应头:
		response.setHeader(String key,String value);设置字符串形式的响应头
		response.addHeader(String key,String value);追加响应头, 若之前设置设置过这个头,则追加;若没有设置过,则设置
	设置定时刷新:
		response.setHeader("refresh","3;url=/day0901/login.htm");

### 统计登陆成功的次数
技术分析:**ServletContext**
常用方法：
   
   ```java 
    1.setAttribute(String key,Object value);//设置值
	2.Object getAttribute(String key);//获取值
	3.removeAttribute(String key)://移除值
   ```

获取全局管理者:getServletContext()。

###ServletContext
作用：

    1.获取全局的初始化参数
    2.共享资源(xxxAttribute)
    3.获取文件资源
    4.其他操作
    
获取servletcontext：

    1.getServletConfig().getServletContext()了解
    2.getServletContext();    

常用方法:

		1.了解
			String  getInitParameter(String key):通过名称获取指定的参数值
			Enumeration getInitParameterNames() :获取所有的参数名称	
			 在根标签下有一个 context-param子标签 用来存放初始化参数
				<context-param>
					<param-name>encoding</param-name>
					<param-value>utf-8</param-value>
				</context-param>
		2.xxxAttribute
		3.String getRealPath(String path):获取文件部署到tomcat上的真实路径(带tomcat路径)
				getRealPath("/"):D:\javaTools\apache-tomcat-7.0.52\webapps\day09\
			InputStream getResourceAsStream(String path):以流的形式返回一个文件
		4.获取文件的mime类型  大类型/小类型
			String getMimeType(String 文件名称)


###域对象(即ServletContext)

启动与销毁实际
>启动：当项目启动的时候，服务器会为**每一个项目创建一个Servletcontext**
>销毁：项目被移除或者服务器关闭的时候。


###获取文件路径
**获取classes目录下文件的路径**
xxx.class.getClassLoader().getResource("2.txt").getPath()


