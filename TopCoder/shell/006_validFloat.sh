#!/bin/sh
. 005_validint.sh

validFloat() {
    fvalue="$1"

    if [[ ! -z $(echo $fvalue | sed 's/[^.]//g') ]] ; then
        echo $fvalue
        decimalpart="$(echo $fvalue | cut -d. -f1)"
        fractionalpart="$(echo $fvalue | cut -d. -f2)"

        echo "${decimalpart}"
        echo "${fractionalpart}"

        if [[ ! -z ${decimalpart} ]]; then
           if ! validint "$decimalpart" "" "" ; then
              return 1
           fi
        fi

        if [[ "${fractionalpart%${fractionalpart#?}}" = "-" ]]; then
            echo "Invalid floating point number: '-' not allowed \
	            after decimal point" >&2
            return 1
        fi

        if [[ "${fractionalpart}" != "" ]]; then
            if ! validint ${fractionalpart} "" ""; then
                return 1
            fi
        fi

        if [ "$decimalpart" = "-" -o -z ${decimalpart} ]; then
            if [[ -z ${fractionalpart} ]];then
                echo "Invalid floating point format." >&2 ; return 1
            fi
        fi

    else
        if [[ "${fvalue}" = "-" ]] ; then
            echo "Invalid floating point format." >&2 ; return 1
        fi

        if ! validint "$fvalue" "" "" ; then
            return 1
        fi
    fi
    return 0
}


if validFloat $1 ; then
  echo "$1 is a valid floating point value"
fi

exit 0