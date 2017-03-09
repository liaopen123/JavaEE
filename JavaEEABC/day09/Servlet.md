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

编写一个servlert的步骤：

		1.编写一个类
			a.继承HttpServlet
			b.重写doGet或者doPost方法
		2.编写配置文件(web-info/web.xml)
			a.注册servlet
			b.绑定路径
		3.访问：
			http://主机:端口号/项目名/路径
		
如何接受提交的参数:

	```java
	String value = request.getParameter("key");


回写内容：

	response
	response.getWriter().print("内容");
	//处理乱码  在最前面 写上：
	resp.setContentType("text/html;charset=utf-8");