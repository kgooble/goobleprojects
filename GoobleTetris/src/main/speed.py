'''
Created on 2012-02-04

@author: Gooble
'''

LEVEL_THRESHOLDS = {}
for i in range (0, 20):
    LEVEL_THRESHOLDS[i * 5000] = 48 - i * 2

class Speed(object):
    
    def for_score(self, score):
        for threshold in sorted(LEVEL_THRESHOLDS.keys(), reverse=True):
            if score >= threshold:
                return LEVEL_THRESHOLDS[threshold]
        return 48 
