```

git config --global user.user.name 'github用户名'  配置用户名

git config --global user.user.email 'github邮箱'  配置邮箱

cd +路径  进入此工作目录

git init  创建仓库

git add  文件名字     --添加到仓库

git commit -m '注释信息'   --提交到仓库

git status  查看更新信息(添加但未提交)

git diff  查看修改添加的内容

git log 查看提交日志

git log --pretty=oneline 查看提交版本号

git reset --hard HEAD^  回退到上一版本

git reset --hard HEAD^^  回退到上上版本

git reset --hard HEAD-100 回退到上100个版本

git reset --hard 版本号  回退到具体版本

git reflog 记录每一次操作命令

```

git的工作区和版本库

```
工作区的文件通过 add 添加到缓存区 缓存区里的通过 commit 命令添加到版本库
git checkout -- 文件名   
	撤销(倘若没有add,但是对文件进行了操作,回退到版本库)
	倘若add,没有添加到版本库,但是做了修改,回退到暂存区(及add后的状态)
	总之就是最后一次的add或commit状态git 
```

远程仓库连接

```
ssh-keygen -t rsa -C '邮箱地址'   :记住路径并回车
cd 之前记住的路径 
ls查看目录 id_rsa(私钥)  id_rsa.pub(公钥)
cat 查看并复制 id_rsa.pub
登录github settings  SSH and GPG keys
添加公钥
ssh -T git@github.com 测试
git remote add origin 远程仓库地址()
删除关联 git remote rm origin
git pull origin master --allow-unrelated-histories 拉代码
git push origin master 推到远程仓库
git clone 远程仓库地址()  克隆远程仓库(到本地,自带丶git文件)
创建 .gitignore 将不需要上传的文件的文件名记录其中 上传时忽略
```

分支

```
git branch <分支名>  创建分支
git checkout <分支名> 切换分支
git checkout -b <分支名> 创建并切换分支
git marge <分支名> 合并分支
git bradch -d <分支名> 删除分支
git push origin master --delete<分支名> 删除远程仓库分支
```

