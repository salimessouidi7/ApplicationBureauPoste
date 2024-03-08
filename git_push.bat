@echo off

REM Check if a commit message is provided as a command-line argument
if "%1"=="" (
    set commit_message="commit after changes"
) else (
    set commit_message=%1
)

git add .
git commit -m "%commit_message%"
git push origin master
