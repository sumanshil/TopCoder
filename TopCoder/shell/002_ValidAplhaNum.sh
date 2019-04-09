#!/usr/bin/env bash

validAlphaNum()
{
    compressed = "$(echo $1 | sed -e 's/[^[:alnum:]]//g')"
    if [[ "$compressed" != "$input" ]]; then
        return 1
    else
        return 0
    fi
}

echo -n "Enter Input: "
read input

if ! validAlphaNum "$input"; then
   echo "Your input must consist of only letters and numbers." >&2
   exit 1
else
   echo "Input is valid"
fi