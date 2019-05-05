#!/usr/bin/env bash

validint() {

  number="$1"; min="$2"; max="$3"
  echo "Hi"
  if [[ -z ${number} ]] ; then
    echo "You didn't enter anything. Unacceptable." >&2 ; return 1
  fi

  if [[ "${number%${number#?}}" = "-" ]]; then
    testvalue="${number#?}"
  else
    testvalue="$number"
  fi

  nodigits="$(echo $testvalue | sed 's/[[:digit:]]//g')"

  echo "${testvalue}"

  if [[ ! -z ${nodigits} ]]; then
    echo "Invalid number format! Only digits, no commas, spaces, etc." >&2
    return 1
  fi

  if [[  ! -z ${min} ]]; then
     if [[ ${testvalue} -lt ${min} ]]; then
        echo "Your value is too small: smallest acceptable value is $min" >&2
        return 1
     fi
  fi

  if [[ ! -z ${max} ]]; then
    if [[ ${testvalue} -gt ${max} ]]; then
       echo "Your value is too big: largest acceptable value is $max" >&2
       return 1
    fi
  fi

  return 0
}

#validint $1 $2 $3