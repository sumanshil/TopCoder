

def decodeMorse(morse_code):
    MORSE_CODE = {}
    MORSE_CODE["...."] = "H";
    MORSE_CODE["."] = "E";
    MORSE_CODE["-.--"] = "Y";
    MORSE_CODE[".---"]= "J";
    MORSE_CODE["..-"] = "U";
    MORSE_CODE["-.."] = "D";

    result = ''
    for word in morse_code.strip().split('   '):
        for char in word.split(' '):
            result = result + MORSE_CODE[char]
        result = result + ' '

    return result[0:len(result)-1]

print(decodeMorse('  .  '))

print(''.join(['2','3','4','5']))