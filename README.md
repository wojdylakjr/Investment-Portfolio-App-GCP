# Investment-Portfolio-App-GCP
 An investment portfolio management app built on Google Cloud Platform services and Terraform infrastructure

#### [PL] Opis aplikacji
Aplikacja do zarządzania portfelem inwestycyjnym umożliwia:

- **Dodawanie i śledzenie zakupionych akcji** 
Użytkownik może wprowadzać szczegóły dotyczące swoich inwestycji, takie jak nazwa spółki, ilość zakupionych akcji, cena zakupu, data i inne istotne informacje.

- ** Monitorowanie bieżącej wartości portfela** 
Dzięki pobieraniu aktualnych cen akcji z zewnętrznego API, aplikacja wyświetla aktualną wartość posiadanych akcji, co umożliwia użytkownikowi śledzenie zmian wartości portfela w czasie rzeczywistym.

- **Otrzymywanie codziennych aktualizacji** 
Aplikacja automatycznie wysyła użytkownikowi codzienne e-maile z aktualnymi wycenami akcji w portfelu. Dzięki temu użytkownik może śledzić wykresy wartości i analizować zmiany, jakie zachodzą na rynku.

- **Planowane technologie**

	- 	**App Engine** - Web server zaimplementowany w języku Java z użyciem Spring Framework
	- 	**Cloud SQL** - Dane dotyczące portfela i transakcji są przechowywane
	- 	**Cloud Functions** - Wykorzystane raz dziennie do pobrania wyceny portfela i wysłanie maila do uzytkownika przy wykorzystaniu zewnetrznego serwera.
	- 	**Terraform** - Infrastruktura aplikacji

<img width="1061" alt="image" src="https://github.com/wojdylakjr/Investment-Portfolio-App-GCP/assets/62746610/1b41a9a5-d40b-4e27-8b33-6cc8fe9258a7">
