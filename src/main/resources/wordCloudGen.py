# #-- coding:UTF-8 --
import os.path
from wordcloud import WordCloud
import jieba  # 词语切割


def gen_wordcloud_pic(content_list, pic_name, stopword_path, pic_save_dir):
    stopwords = set()
    stopwords_content = [line.strip() for line in open(stopword_path, 'r', encoding='utf-8').readlines()]
    stopwords.update(stopwords_content)

    all_content_str = ''
    for content in content_list:
        text_cut = jieba.cut(content, cut_all=False)
        all_content_str = all_content_str + ' '.join([ele for ele in text_cut if len(ele) > 1])

    wc = WordCloud(width=900, height=700,
                   background_color='white',
                   # mode='RGB',
                   max_words=120,
                   font_path='C:\Windows\Fonts\simkai.ttf',
                   # max_font_size=10,
                   relative_scaling=0.6,  # 设置字体大小与词频的关联程度为0.4
                   # random_state=42,
                   scale=1,
                   stopwords=stopwords
                   ).generate(all_content_str)

    if not os.path.exists(pic_save_dir):
        os.makedirs(pic_save_dir)

    save_path = pic_save_dir + pic_name
    if os.path.exists(save_path):
        os.remove(save_path)

    wc.to_file(save_path)

# def gen_wordcloud_pic(content_list: list, pic_name, pic_save_dir):
#     count = len(content_list)
#     print(count)
#     return count
