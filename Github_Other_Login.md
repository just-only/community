## 第三方登录

 #### 简介
   在一个网站或者客户端使用其他服务端账号进行登录
 #### 使用协议
   ###### oauth 2.0
    介绍
     OAuth（开放授权）是一个开放标准，允许用户让第三方应用访问该用户在某一网站上存储的私密的资源（如照片，视频，联系人列表），而无需将用户名和密码提供给第三方应用。 ---- 百度百科
     
     这个协议在认证和授权的时候涉及到：
     
     服务提供方，例如 GitHub，GitHub上储存了用户的登录名，Email，昵称，头像等信息
     用户
     客户端，例如我的博客就是一个客户端，需要服务方向我提供用户的一些基本信息
    认证流程
      - 用户打开博客，想要通过GitHub获取用户的基本信息
      - 转跳到GitHub的授权页面后，用户同意我获取他的基本信息
      - 博客获得GitHub提供的授权码，该授权码用于向GitHub申请令牌
      - GitHub对博客提供的授权码进行验证，验证无误后，发放令牌给博客端
      - 博客端使用令牌，向GitHub获取用户信息
      - GitHub 确认令牌无误，返回给基本的用户信息
    
##### github第三方登录
  ####前提操作
    Github申请第三方登录app
    官方教程地址: https://developer.github.com/apps/building-oauth-apps/
  ###### 操作
    官方教程地址：https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/
    1.github登录授权页面
      此处主要是在前端代码中明确写出跳转地址以及参数
      跳转地址：https://github.com/login/oauth/authorize
      参数：
      client_id：创建 OAuth应用时github官网生成的id。用于准确找到相应app
      redirect_uri：创建OAuth应用时填写的回调地址
      scope：携带的用户内容
      state：随机字符串。它用于防止跨站点请求伪造攻击
      最后填充的地址类似如下：
      https://github.com/login/oauth/authorize?client_id=sahdjha1jd3hsa5&redirect_uri=http://localhost:8080/callback&scope=user&state=1
    
    2.获取授权码
      在访问授权页面之后，会返回授权码，用于申请令牌
      使用回调网址的资源地址，来填写相应的Controller
      例如：
      此处的回调地址为：http://localhost:8080/callback
      因此需要填写为callback来创建Controller用于获取申请码
      传递参数 code state
      类：AutherizeController
      方法：callback
    3.根据code申请令牌token
      token的获取主要是根据code来访问网址：https://github.com/login/oauth/access_token 
      项目此处：
      callback方法下根据填充AccessToken实例
      使用GitHub支持类GithubProvider来获取token
    4.根绝token获取用户信息
      根据token令牌去获取访问网址："https://api.github.com/user?access_token=.......
      来获取用户信息
      用户信息中主要是id来标识唯一用户
      项目此处：
      callback 方法下根据 获取的accesstoken
      使用GitHubProvider下的getUser方法来获取用户      
   