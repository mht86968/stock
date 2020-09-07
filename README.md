git 配置
```sh
git config --global user.email "you@example.com"
git config --global user.name "Your Name"
```

git
```sh
git clone   克隆版本库
git add .   添加所有改变
git rm      删除文件
git rm -rf  删除文件夹
git diff    比较不同
```

git 放弃本地修改 强制更新
```sh
git fetch --all
git reset --hard origin/master
```
git 更新
```sh
git pull    更新远程服务器
```
git 提交
```sh
git commit -m             提交改变
git push -u origin master 提交到远程服务器
git push                  提交到远程服务器
```

git 分支
```sh
git branch -a           查看分支
git branch branchname   新建分支
git branch -d newbranch	删除分支
git checkout branchname	切换分支
git merge branchname    合并分支
```
