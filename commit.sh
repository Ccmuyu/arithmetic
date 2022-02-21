# 一键git commit代码，只需要输入提交信息即可
# git status | grep 'new file' | head -1 | awk -F ' ' '{ print $3 }'

git_status=`git status`
echo $git_status

file_name=`git status | grep -e 'new file' -e 'modified:' -e 'deleted'| awk '{ print }'`

echo "存在待提交的文件："
echo $file_name


if [ ! -n "$file_name" ]; then
  echo "代码无变更！跳过提交。"
else
  echo "准备提交代码！"
  git add .
  echo "git add 成功！"

  echo "请输入您的提交信息："
  echo "git commit -m"
  #提交msg，接收控制台输入
  read msg
  echo "您的提交信息：$msg"
  echo "================================"
  echo "$printf"
  git commit -m "$msg"
  echo "git push"
  git push
fi

