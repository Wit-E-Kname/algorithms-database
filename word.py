import os
import pandas as pd
import nltk
import gensim
from gensim import corpora, models, similarities
# reference: https://www.youtube.com/watch?v=thLzt3D-A10
# reference: https://www.youtube.com/watch?v=pY9EwZ02sXU <=  ||Source||
df=pd.read_csv('jokes.csv');

x=df['Question'].values.tolist()
y=df['Answer'].values.tolist()

corpus= x+y
  
tok_corp= [nltk.word_tokenize(sent.decode('utf-8')) for sent in corpus]
       
           
model = gensim.models.Word2Vec(tok_corp, min_count=1, size = 32)

#model.save('testmodel')
model = gensim.models.Word2Vec.load('testmodel')
model.most_similar('hi')
model['hi']
model.most_similar(positive=['woman', 'king'], negative=['man'], topn=1)
#model.most_similar([vector])
# u'\u0930\u093e\u092e\u092a\u093e\u0932'