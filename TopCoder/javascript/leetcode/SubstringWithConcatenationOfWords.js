var findSubstring = function(s, words) {

    var wordMap = new Map();

    for (var i = 0 ; i < words.length ; i++) {
        if (!wordMap[words[i]]) {
            wordMap[words[i]] = 1;
        } else {
            wordMap[words[i]] = wordMap[words[i]] + 1;
        }
    }
    var result = new List();
    var wordLength = words[0].length
    var windowLength = wordLength * words.length;
    i = 0;
    for (var i = 0 ; i < wordLength ; i++) {
        var j = 0;
        var currMap = new Map();
        for (j = i ; j < i + windowLength && j+wordLength <= s.length; j++ ) {
            var substr = s.substr(j, wordLength);
            if (currMap[substr]) {
                currMap[substr] =  currMap[substr] + 1;
            } else {
                currMap[substr] =  1;
            }
        }

        if (currMap === wordMap) {
            result.add(i);
        }

        var left = i;
        while ( j + wordLength <= s.length ) {
            var leftstr = s.substr(left, wordLength);
            var rightstr = s.substr(j, wordLength);

            if (currMap[leftstr] && currMap[leftstr] === 1) {
                delete  currMap[leftstr];
            } else if (currMap[leftstr] && currMap[leftstr] > 1) {
                currMap[leftstr] = currMap[leftstr] - 1;
            }

            if (currMap[rightstr]) {
                currMap[rightstr] = currMap[rightstr] + 1;
            } else {
                currMap[rightstr] = 1;
            }

            left = left + wordLength;
            j = j + wordLength;
            if (currMap === wordMap) {
                result.add(left)
            }
        }

    }
    return result;
};

s = "barfoothefoobarman";
words = ["foo","bar"];

result = findSubstring(s, words);
for (v in result) {
    console.log(v);
}
