import json
from bs4 import BeautifulSoup
from requests import get


players = ""

for page in range(1,39):

	print("page: ", page)

	url = 'https://www.futhead.com/19/players/?group=gk&page=' + str(page) + '&bin_platform=ps'

	response = get(url)

	html_soup = BeautifulSoup(response.text, 'html.parser')

	player_containers = html_soup.find_all('li', class_='player-group-item')[1:]


	for player_container in player_containers:
		
		player_url_html = player_container.find('a', class_='display-block')
		player_url_str = str(player_url_html)
		urlStartIndex = player_url_str.find("/")
		
		urlEndIndex = player_url_str.find("\">")
		
		player_url = 'https://www.futhead.com' + player_url_str[urlStartIndex:urlEndIndex]

		player_response = get(player_url)

		player_html = BeautifulSoup(player_response.text, 'html.parser')


		table = player_html.find('div', id='info-tab')

		if (table == None):
			continue

		player_data = table.find_all('a', class_='futhead-link')

		player_team = []

		for data in player_data:
			dataStr = str(data)
			startIndex = dataStr.find(">") + 1
			endIndex = dataStr.find("</")
			information = dataStr[startIndex:endIndex]
			player_team.append(information)

		if (len(player_team) < 3):
			continue

		club = player_team[0]
		league = player_team[1]
		country = player_team[2]
		

		player_card = player_html.find('div', class_='playercard')
		player_rating_html = player_card.find('div', class_='playercard-rating')
		player_name_html = player_card.find('div', class_='playercard-name')
		player_position_html = player_card.find('div', class_='playercard-position')
		
		player_data_html = [player_rating_html, player_name_html, player_position_html]
		
		for html in player_data_html:
			dataStr = str(html).replace(" ", "").replace("\n", "").strip()
			startIndex = dataStr.find(">") + 1
			endIndex = dataStr.find("</")
			information = dataStr[startIndex:endIndex]
			player_team.append(information)

		if (len(player_team) < 6):
			continue

		rating = player_team[3]
		name = player_team[4]
		position = player_team[5]

		player = name + "," + position + "," + rating + "," + club + "," + league + "," + country + "\n"


		players += player
		print(player)




f = open("players_1.txt", "w")
f.write(players)
f.close()
	
