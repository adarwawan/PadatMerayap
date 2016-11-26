import requests
from lxml import html
from datetime import datetime
import re

class JadwalBola(object):
    def __init__(self):
        self.date_now = datetime.now()

    # date merupakan objek datetime.now()
    def getJadwal(self, date):
        day = date.day
        month = date.month 
        year = date.year
        print(day)
        print(month)
        print(year)
        with open('test.html', 'r') as myfile:
            data=myfile.read().replace('\n', '')
        tree = html.fromstring(data)
        schedule = tree.xpath('//table[@id="tablepress-17"]//td[@class="column-2"]/text()')
        with open("Output.txt", "w") as text_file:
            for sch in schedule:
                sch = re.sub('\Jl. ', '', sch)
                sep = ','
                sch = sch.split(sep, 1)[0]
                text_file.write("%s\n" % sch)
        return schedule


if __name__ == "__main__":
    jadwal = JadwalBola()
    jadwal.getJadwal(jadwal.date_now)
