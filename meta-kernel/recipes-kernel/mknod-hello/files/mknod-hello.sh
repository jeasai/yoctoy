#!/bin/sh

# Finds 'hello' major number using /proc/devices
MAJOR="$(sh -c 'cat /proc/devices | grep -o "[0-9]* hello\$" | sed "s/ [A-Za-z]*//g"')"

if [ "${MAJOR}" != "" ]
then
    mknod /dev/hello c "${MAJOR}" 0
else
    return 1
fi

return 0
