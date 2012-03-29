'''
Created on 2012-03-28

@author: Gooble
'''

import urllib.request
import html.parser

def fix(bytesline):
    return bytesline.decode()

class LyricParser(html.parser.HTMLParser):
    
    def __init__(self):
        html.parser.HTMLParser.__init__(self)
        self.start_lyric = None
        self.start_lyric_set = False
        self.lyric_data = None
        self.lyric_data_set = False
        self.end_lyric = None
    
    def handle_starttag(self, tag, attrs):
        if tag.find("div") != -1 and ('class', 'lyricbox') in attrs:
            self.start_lyric = self.getpos()
            self.start_lyric_set = True
            
    def handle_data(self, data):
        if self.start_lyric_set:
            self.lyric_data = self.getpos()
            self.start_lyric_set = False
            self.lyric_data_set = True
            
    def handle_endtag(self, tag):
        if self.lyric_data_set:
            self.lyric_data_set = False
            self.end_lyric = self.getpos()
            

if __name__ == '__main__':
    page = urllib.request.urlopen("http://lyrics.wikia.com/Radiohead:Separator")
    parser = LyricParser()
    giant_html_string = ""
    for line in page:
        giant_html_string += (parser.unescape((fix(line))))
        
    parser.feed(giant_html_string)
    
    print(parser.start_lyric)
    print(parser.lyric_data)
    print(parser.end_lyric)
    
    #print(giant_html_string[parser.start_lyric[0]:parser.lyric_data[0]])
    i = 0
    for line in giant_html_string.split("\n"):
        if i >= parser.start_lyric[0] and i <= parser.lyric_data[0]:
            print(line)
        i += 1
    print(i)
    #page.seek(parser.start_lyric[0])
    #print(page.read(parser.lyric_data[0]))