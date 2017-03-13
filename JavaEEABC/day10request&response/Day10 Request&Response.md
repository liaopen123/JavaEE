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

