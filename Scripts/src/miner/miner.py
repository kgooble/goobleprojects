'''
Created on 2011-10-06

@author: Gooble
'''

import urllib.request
import re

if __name__ == '__main__':
    opener = urllib.request.build_opener()
    f = opener.open('http://www.metrolyrics.com/the-waiting-game-lyrics-broken-bells.html')
    stuff = f.read()
#    stringstuff = stuff.decode('utf-8')
    stringstuff = stuff.decode('latin_1')
#    stringstuff = stringstuff.replace('\u2032', '\'')
#    stringstuff = stringstuff.encode("ascii")
#    lines = stringstuff.split('\n')
    
    pattern = re.compile('<body>(.*)</body>', re.DOTALL)
    
    match = re.match(pattern, stringstuff)
    if match is not None:
        print (match.group(1))
#    print (stringstuff)
#    stringstuff = str(stringstuff)
#    print(stringstuff)
    
#    for line in lines:
#        print (line)