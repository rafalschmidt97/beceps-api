#!/bin/sh
# this hook is in SCM so that it can be shared
# to install it, create a symbolic link in the projects .git/hooks folder
#
#       i.e. - from root directory run
#               $ chmod 777 ./utils/pre-commit.sh
#               $ cp ./utils/pre-commit.sh .git/hooks/pre-commit
#
# to skip the tests, run with the --no-verify argument
#       i.e. - $ 'git commit --no-verify'

echo "Pre commit hook 🚀:"

if git diff --cached --quiet; then
    echo "No changes added to commit!️"
    exit 1
else
    ./gradlew check
fi

