# ProjectCC

Projekt napisany dla samego siebie, użytkowy, działający.
Aktualnie pracuję w Call Center program służy do dodawania zmian (godziny pracy), dodawania prowizji za umowy, dodawania zmian kupionych oraz 
liczenia statystyk dotyczących zarobków liczby godzin itd. Wszystko to zostaje zapisane w lokalnej bazie danych z wykorzystaniem MySQL i JDBC.

Program posiada GUI wykonany za pomocą Java FXML. Posiada 7 widoków:
1) główny widok do wybrania 1 z 6 opcji

2) Widok służący do dodawania zmiany z grafiku (zmiana = zakres godzin pracyw  danym dniu)
- daty zmiany
- zakres godzin zmiany w danym dniu
- przycisk po wciśnieciu którego zostaje przeprowadzona prosta walidacja i potem po potwierdzeniu wysłaniu dancych do bazy danych

3)Widok służący do dodawania zmiany kupionej/sprzedanej ( Tutaj jest więcej zmiennych do dodania, kupujemy zmianę od innej osoby)
- data zmiany
- zakres godzin zmiany w danym dniu
- kwota za jaką zmiana została kupiona/sprzedana
- osoba od której zmiana został kupiona lub komu sprzedana
- radio button (2 opcje) - pieniądze do wypłaty/ gotówka
- radio button (2 opcje) - zmiana sprzedana/kupiona
- przycisk po wciśnieciu którego zostaje przeprowadzona prosta walidacja i potem po potwierdzeniu wysłaniu dancych do bazy danych

4) Widok służący do dodania prowizji z umowy
- data zapisania umowy
- radio button - (2 opcje) typ umowy umowa/zgoda
- wartość prowizji za daną umowę
- nazwa umowy
- przycisk po wciśnieciu którego zostaje przeprowadzona prosta walidacja i potem po potwierdzeniu wysłaniu dancych do bazy danych

5)Widok przedstawiający wszystkie zmiany 
- Zmiany pobrane z bazy danych wyświetlone w tabeli
- możwliowość filtrowania zmian w danym miesiącu
- możliwość usunięcia danej zmiany, powoduje usunięcie z bazy danych

6)Widok przedstawiający prowizje 
- Zmiany pobrane z bazy danych wyświetlone w tabeli
- możwliowość filtrowania zmian w danym miesiącu
- możliwość zaznaczanie czy umowa została zaakceptowana przez klienta 
- możliwość usunięcia danej zmiany, powoduje usunięcie z bazy danych

7) Statystyki
- Łączne zarobki w danym zakresie czasu
- zarobki ze zmian z grafiku
- zarobki ze zmian kupionych/sprzedanych
- zarobki z prowizji
- liczba godzin 
- liczba godzin z grafiku
- liczba godzin kupioncych/sprzedanych
-  i inne

Program pisałem sam dla siebie i wykorzystuję go do dnia dziśejszego. Pisałem w wolnej chwili podczas nauki Javy.
Jeśli chodzi o bazy danych, wykorzystałem głównie JDBC i bazy danych MySql lokalnie na moim PC. Technologi JEE i JDBC nigdy się nie uczyłem
w tym projekcie korzystałem z internetu jeśli coś mi było potrzebne. Język SQL znam w stopniu podstawowym. Podstaw Javy FXML uczyłem się
także z kursu javastart.pl.

Gdybym pisał projekt teraz wykorzystałebym Hibernate który jest dużo lepszym rozwiązaniem.




