'''
Created on 2011-10-06

@author: Gooble
'''

import urllib.request
import re

if __name__ == '__main__':
    opener = urllib.request.build_opener()
    f = opener.open('http://www.azlyrics.com/lyrics/radiohead/separator.html')
    stuff = f.read()
    stringstuff = str(stuff, encoding='utf8')
    
    lines = stringstuff.split('\n')
    
    p = re.compile('falling')
    for line in lines:
        print (line)