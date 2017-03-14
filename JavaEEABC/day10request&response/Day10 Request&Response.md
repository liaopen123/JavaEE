# Day10 Request&Response

##Response
响应行内容
>协议/版本(固定)  状态码

状态码说明：
>1XX:已经发送请求
>2XX:已经完成相应
>3XX:还需浏览器进一步操作
>>302:重定向 **配合响应头：location**
>>304:读缓存

>4XX:用户操作错误
>>404：用户操作错误
>>405：访问的方法不存在

>5XX：服务器错误

###操作响应头：

响应头格式:
>key/value(value可以是多个值)


常用方法：


```java
    setStatus(int 状态码) 适用于1XX2XX3XX
    sendError(int 状态码) 针对于 4XX5XX
    setHead("key","value");
    setIntHead("key",int);
    setDateHead("key",long)；
    
    addHead("key","value");之前设置过就追加 没设置过就设置
```

常用响应头：
>location:重定向
>refresh：定时刷新
>content-type:设置文件的mime类型 极其告诉浏览器用什么编码打开
>content-disposition：文件下载需要的头

重定向：

    方法1：
    response.setStatus(302);
	response.addHeader("Location", "/day10/Location2");
    方法2：response.sendRedirect("/day10/Location2");
    
    
    
定时刷新：
    
    主要通过js去做这个事情
    1.在html文件中添加：<meta http-equiv="refresh" content="3;url=/day10/refresh2.html">
    其中3为刷新的时间  url为跳转的地址
    2.通过js的interval去修改文字
    <script type="text/javascript">
    onload=function(){
	   setInterval(changes, 1000);
    }
    	i=3;
    function changes(){
      var obj =  document.getElementById("sid");
         obj.innerHTML=--i;
    }
    </script>
    
###操作响应体

常用方法：

    Writer getWriter();字符流
    ServletOutputStream getOutputStream();字节流
    
    自己写的东西用字符流 其他一律用字节流

    
这两个互斥servlet中不能同时使用
响应完成后，服务器会判断stream是否关闭，如果没关  服务器会自动关流。


###文件下载
下载的方式
    
    
    1.超链接下载
            <a href="/day10/1.text">下载</a>
            若浏览器能解析的mime类型则浏览器内部打开，否则下载
    2.编码下载，通过servlet下载
    <a href="/day10/download?name=bankInfo.text">下载</a> 
        a.设置文件的mime类型
            String mimeType=context.getMimeType(文件名)
				response.setContentType(mimeType);
        b.设置文件的下载头信息
        response.setHeader("content-disposition", "attachment;filename="+文件名称);
        c.提供流
        response.getOutputStream();
        
### 验证码
点击换一张的js代码:


		function changeImg(obj){
			//操作src属性
			obj.src="/day10/code?i="+Math.random();
			//alert(1)
		}        
        
        
##Request
常用方法：
>掌握：
>>String getMethod():获取请求方式
				String getRemoteAddr():获取ip地址
				String getContextPath() :在java中获取项目名称  (/day10)

>了解：
>>getRequestURI():获取的是 从项目名到参数之前的内容  /day10/regist
				getRequestURL():获取的带协议的完整路径   http://localhost/day10/regist
				String getQueryString():get请求的所有参数   username=tom&password=123
				String getProtocol():获取协议和版本
				
				
操作请求头：
    
    
```java
掌握
    String value =     getHeader(String key);
了解    
    Enumeration getHeaders(String name) :通过key获取指定的value(多个)
    				Enumeration getHeaderNames() :获取所有的请求头的名称
    				int getIntHeader(String key):获取整型的请求头
    				long getDateHeader(String key):获取时间的请求头
```

重要的头信息：

    user-agent:浏览器类型
    referer：来自哪  防盗链
    
    
操作请求参数：

    
    例如参数为   username=tom&password=123&hobby=drink&hobby&sleep    

常用方法★：

    String getParameter(String key):获取一个值
	String[] getParameterValues(String key):通过一个key获取多个值
	Map<String,String[]> getParameterMap():获取所有的参数名称和值
	
	
请求参数中文乱码的处理：

    乱码原因：
        GET请求参数拼在链接中，浏览器是用UTF-8进行编码，而Tomcat是用采用ISO-8859-1,因而产生乱码
        Post请求也一样
    解决办法：
         GET
        new String(参数.getBytes("iso-8859-1"),"utf-8");
        POST
        针对于post请求来说:只需要将请求流的编码设置成utf-8即可
			request.setCharacterEncoding("utf-8");     

下载文件的文件名乱码情况：

    中文名称的文件名下载的时候名称会出现问题
	常见的浏览器需要提供文件名称的utf-8编码
	对于火狐来说需要提供文件名称的base64编码
		方案1:使用工具类
		方案2:网上的方式(8成好使)
			new String(filename.getByte("gbk"),"iso8859-1");

Request的域对象

    创建：每次请求来的时候
    销毁：响应生成的时候
    作用：存放一次请求里的数据 
    
    应用：
        请求转发(请求Aservlet，a搞定不了，转发给Bservlet)
    代码：
        request.getRequestDispatcher("内部路径：/dis2").forward(request,response);
        
 
 
 
 请求转发和重定向的区别(面试题)
 
    重定向发送两次请求,请求转发一次请求
	
	重定向地址栏发生该表,请求转发不变
	
	重定向是从浏览器发送,请求转发是服务器内部
	
	重定向不存在request域对象,请求转发可以使用request域对象
	
	重定向是response的方法,请求转发是request的方法
	
	重定向可以请求站外资源,请求转发不可以		
	
               

