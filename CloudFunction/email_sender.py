import functions_framework
import requests
from mailjet_rest import Client


@functions_framework.http
def hello_http(request):
    api_key = ''
    api_secret = ''

    mailjet = Client(auth=(api_key, api_secret))

    url = 'https://testproject-410111.lm.r.appspot.com/api/wallet/1/create-record'
    response = requests.post(url, json={})
    print(response)

    if response.status_code == 200:
        email_content = f'RESULT: {response.text}'
    else:
        email_content = 'Nie udało się wykonać zapytania POST'

    data = {
        'FromEmail': 'jrwojdylak@student.agh.edu.pl',
        'FromName': 'Mailjet Pilot',
        'Subject': 'Your email flight plan!',
        'Text-part': email_content,
        'Html-part': '',
        'Recipients': [{'Email': 'wojdylakjr@gmail.com'}]
    }

    try:
        result = mailjet.send.create(data=data)
        return f"Email status code: {result.status_code}, Response: {result.json()}"
    except Exception as e:
        return f"Error: {str(e)}"
