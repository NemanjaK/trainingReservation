insert into uloga(naziv) values ('korisnik');

insert into korisnik(broj_telefona, email, ime, password, prezime, uloga_id) values ('0642131254', 'pera@gmail.com', 'Pera', '123', 'Peric', 1);

insert into trening(datum_treninga, opis) values('2021-02-11','Grace');
insert into trening(datum_treninga, opis) values('2021-02-11','FRAN');
insert into trening(datum_treninga, opis) values('2021-11-11','FRAN');

insert into rezultat(broj_rundi, vreme, korisnik_id, trening_id) values ('4', '20:00', 1, 1);

insert into termin(vreme, trening_id) values ('2021-02-11 12:00:00', 1);
insert into termin(vreme, trening_id) values ('2021-02-11 13:00:00', 1);

insert into rezervacija(termin_id) values(1);
insert into rezervacija(termin_id) values(2);
