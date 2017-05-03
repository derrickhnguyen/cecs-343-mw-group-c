package com.groupc.android.illuminati.Objects;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
        initializeCards();
        shuffle();
    }

    private void initializeCards() {
        initializeGroupCards();
        initializeSpecialCards();
    }

    private void shuffle() {
        ArrayList<Card> temp = new ArrayList<Card>();
        while(!cards.isEmpty()) {
            int x = (int) (Math.random()*cards.size());
            temp.add(cards.get(x));
            cards.remove(x);
        }
        cards = temp;
    }

    public Card draw() {
        return cards.remove(0);
    }

    public void returnToDeck(Card card) {
        cards.add(card);
        shuffle();
    }



    private void initializeGroupCards() {

        //to be added to a database

        Table.CardTypeEnum group = Table.CardTypeEnum.GROUP;
        GroupCard americanAutoduelAssociation = new GroupCard(
                "American Autoduel Association",
                group,
                1, 0, 5, 1,
                new Table.AlignmentEnum[]{Table.AlignmentEnum.WEIRD},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(americanAutoduelAssociation);

        GroupCard antiNuclearActivists = new GroupCard(
                "Anti Neuclear Activists",
                group,
                2, 0, 5, 1,
                new Table.AlignmentEnum[]{Table.AlignmentEnum.LIBERAL},
                Table.SpecialAbilityEnum.PLUSTWOONANYATTEMPTTODESTROYNUCLEARPOWERCOMPANIES
        );
        cards.add(antiNuclearActivists);

        GroupCard antiwarActivists = new GroupCard(
                "Antiwar Activists",
                group,
                0, 0, 3, 1,
                new Table.AlignmentEnum[]{Table.AlignmentEnum.PEACEFULL, Table.AlignmentEnum.LIBERAL},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(antiNuclearActivists);

        GroupCard bigMedia = new GroupCard(
                "Big Media",
                group,
                4, 3, 6, 3,
                new Table.AlignmentEnum[]{Table.AlignmentEnum.LIBERAL, Table.AlignmentEnum.STRAIGHT},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(bigMedia);

        GroupCard boySprouts = new GroupCard(
                "Boy Sprouts",
                group,
                0, 0, 3, 1,
                new Table.AlignmentEnum[]{Table.AlignmentEnum.STRAIGHT, Table.AlignmentEnum.PEACEFULL},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(boySprouts);

        GroupCard california = new GroupCard(
                "California",
                group,
                5, 0, 4, 5,
                new Table.AlignmentEnum[]{Table.AlignmentEnum.LIBERAL, Table.AlignmentEnum.WEIRD, Table.AlignmentEnum.GOVERNMENT},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(california);

        GroupCard cflAio = new GroupCard(
                "CFL AIO",
                group,
                6, 0, 5, 3,
                new Table.AlignmentEnum[]{Table.AlignmentEnum.LIBERAL},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(cflAio);

        GroupCard chineseCampaignDonors = new GroupCard(
                "Chinese Campaign Donors",
                group,
                3, 0, 2, 2,
                new Table.AlignmentEnum[]{Table.AlignmentEnum.COMMUNIST},
                Table.SpecialAbilityEnum.TREATTHISGROUPASGOVERNMENTWHENITATTEMPTSTOCONTROLAGOVERNMENTGROUP
        );
        cards.add(chineseCampaignDonors);

        GroupCard cia = new GroupCard(
                "CIA",
                group,
                6, 4, 5, 0,
                new Table.AlignmentEnum[]{Table.AlignmentEnum.GOVERNMENT, Table.AlignmentEnum.VIOLENT},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(cia);

        GroupCard cloneArrangers = new GroupCard(
                "Clone Arrangers",
                group,
                6, 2, 6, 1,
                new Table.AlignmentEnum[]{Table.AlignmentEnum.VIOLENT, Table.AlignmentEnum.COMMUNIST, Table.AlignmentEnum.CRIMINAL},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(cloneArrangers);

        GroupCard comicBooks =  new GroupCard(
                "Comic Books",
                group,
                1, 0, 1, 2,
                new Table.AlignmentEnum[]{Table.AlignmentEnum.WEIRD, Table.AlignmentEnum.VIOLENT},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(comicBooks);

        GroupCard congressionalWives = new GroupCard(
                "Congressional Wives",
                group,
                1, 0, 4, 1,
                new Table.AlignmentEnum[]{Table.AlignmentEnum.CONSERVATIVE, Table.AlignmentEnum.STRAIGHT},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(congressionalWives);

        GroupCard convenienceStores = new GroupCard(
                "Convenience Stores",
                group,
                1, 0, 4, 3,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.STRAIGHT},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(convenienceStores);

        GroupCard copyShops = new GroupCard(
                "Copy Shops",
                group,
                1, 0, 3, 4,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.PEACEFULL},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(copyShops);

        GroupCard cycleGangs = new GroupCard(
                "Cycle Gangs",
                group,
                0, 0, 4, 0,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.VIOLENT, Table.AlignmentEnum.WEIRD},
                Table.SpecialAbilityEnum.PLUSTWOONANYATTEMPTTODESTROYANYGROUP
        );
        cards.add(cycleGangs);

        GroupCard democrats = new GroupCard(
                "Democrats",
                group,
                5, 0, 4, 3,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.LIBERAL},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(democrats);


        GroupCard ecoGuerrillas = new GroupCard(
                "Eco Guerillas",
                group,
                0, 0, 6, 1,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.LIBERAL, Table.AlignmentEnum.VIOLENT, Table.AlignmentEnum.WEIRD},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(ecoGuerrillas);

        GroupCard emptyVee = new GroupCard(
                "Empty Vee",
                group,
                0, 0, 6, 1,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.NONE},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(emptyVee);

        GroupCard evilGeniusesForABetterTomorrow = new GroupCard(
                "Evil Geniuses for a Better Tomorrow",
                group,
                0, 2, 6, 3,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.VIOLENT, Table.AlignmentEnum.WEIRD},
                Table.SpecialAbilityEnum.PLUSFOURFORANYATTEMPTTOCONTROLNEUTRALIZEORDESTROYTHEORBITALMINDCONTROLLASERS
        );
        cards.add(evilGeniusesForABetterTomorrow);

        GroupCard fastFoodChains = new GroupCard(
                "Fast Food Chains",
                group,
                2, 0, 4, 3,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.STRAIGHT},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(fastFoodChains);

        GroupCard fbi = new GroupCard(
                "FBI",
                group,
                4, 2, 6, 0,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.GOVERNMENT, Table.AlignmentEnum.STRAIGHT},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(fbi);

        GroupCard federalReserve = new GroupCard(
                "Federal Reserve",
                group,
                5, 3, 7, 6,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.GOVERNMENT},
                Table.SpecialAbilityEnum.WHENITTRANSFERSMONEYTHATMONEYCANGOTOANYGROUPINTHESAMEPOWERSTRUCTURE
        );
        cards.add(federalReserve);

        GroupCard feminists = new GroupCard(
                "Feminists",
                group,
                2, 0, 2, 1,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.LIBERAL},
                Table.SpecialAbilityEnum.PLUSTHREEONANYATTEMPTTOCONTROLANYLIBERALGROUP
        );
        cards.add(feminists);

        GroupCard fiendishFluoridators = new GroupCard(
                "Fiendish Flouridators",
                group,
                3, 0, 5, 1,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.COMMUNIST, Table.AlignmentEnum.FANATIC},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(fiendishFluoridators);

        GroupCard flatEarthers = new GroupCard(
                "Flat Earthers",
                group,
                1, 0, 2, 1,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.WEIRD, Table.AlignmentEnum.CONSERVATIVE},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(flatEarthers);

        GroupCard fnordMotorCompany = new GroupCard(
                "Fnord Motor Company",
                group,
                2, 0, 4, 2,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.PEACEFULL},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(fnordMotorCompany);

        GroupCard fraternalOrders = new GroupCard(
                "Fraternal Orders",
                group,
                3, 0, 5, 2,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.CONSERVATIVE},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(fraternalOrders);

        GroupCard girlieMagazines = new GroupCard(
                "Girlie Magazines",
                group,
                2, 0, 2, 3,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.LIBERAL},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(girlieMagazines);

        GroupCard goldfishFanciers = new GroupCard(
                "Goldfish Fanciers",
                group,
                0, 0, 4, 1,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.PEACEFULL, Table.AlignmentEnum.FANATIC},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(goldfishFanciers);

        GroupCard gunLobby = new GroupCard(
                "Gun Lobby",
                group,
                1, 0, 0, 1,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.CONSERVATIVE, Table.AlignmentEnum.VIOLENT},
                Table.SpecialAbilityEnum.NORMALRESISTANCETHREEAGAINSTANYLIBERALCOMMUNISTORWEIRDGROUPRESISTANCETEN
        );
        cards.add(gunLobby);

        GroupCard hackers = new GroupCard(
                "Hackers",
                group,
                1, 1, 4, 2,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.WEIRD, Table.AlignmentEnum.FANATIC},
                Table.SpecialAbilityEnum.PLUSTHREEONANYATTEMPTTONEUTRALIZEANYGROUP
        );
        cards.add(hackers);

        GroupCard healthFoodStores = new GroupCard(
                "Health Food Stores",
                group,
                1, 0, 3, 2,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.LIBERAL},
                Table.SpecialAbilityEnum.PLUSTWOONANYATTEMPTTOCONTROLANTINUCLEARACTIVISTS
        );
        cards.add(healthFoodStores);

        GroupCard hollywood = new GroupCard(
                "Hollywood",
                group,
                2, 0, 0, 5,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.LIBERAL},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(hollywood);

        GroupCard intellectuals = new GroupCard(
                "Intellectuals",
                group,
                0, 0, 3, 1,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.WEIRD, Table.AlignmentEnum.FANATIC},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(intellectuals);

        GroupCard internationalCocaineSmugglers = new GroupCard(
                "International Cocaine Smugglers",
                group,
                3, 0, 5, 5,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.CRIMINAL},
                Table.SpecialAbilityEnum.PLUSFOURONANYATTEMPTTOCONTROLPUNKROCKERSCYCLEGANGSORHOLLYWOOD
        );
        cards.add(internationalCocaineSmugglers);

        GroupCard internationalCommunistConspiracy = new GroupCard(
                "International Communist Conspiracy",
                group,
                7, 0, 8, 6,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.COMMUNIST},
                Table.SpecialAbilityEnum.PLUSTHREEONANYATTEMPTTOCONTROLANYCOMMUNISTGROUP
        );
        cards.add(internationalCommunistConspiracy);

        GroupCard irs = new GroupCard(
                "IRS",
                group,
                5, 3, 5, 0,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.CRIMINAL, Table.AlignmentEnum.GOVERNMENT},
                Table.SpecialAbilityEnum.OWNINGPLAYERMAYTAXEACHOPPONENTTWOMEGABUCKSONHISOWNICOMEPHASETAXMAYCOMEFROMANYGROUPIFAPLAYERHASNOMONEYHEOWESNOTAX
        );
        cards.add(irs);

        GroupCard junkMail = new GroupCard(
                "Junk Mail",
                group,
                1, 0, 3, 2,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.CRIMINAL},
                Table.SpecialAbilityEnum.PLUSFOURONANYATTEMPTTOCONTROLTHEPOSTOFFICE
        );
        cards.add(junkMail);

        GroupCard kgb = new GroupCard(
                "KGB",
                group,
                2, 2, 6, 0,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.COMMUNIST, Table.AlignmentEnum.VIOLENT},
                Table.SpecialAbilityEnum.PLUSTWOONANYATTEMPTTODESTROYANYGROUP
        );
        cards.add(kgb);

        GroupCard kkk = new GroupCard(
                "KKK",
                group,
                2, 0, 5, 1,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.CONSERVATIVE, Table.AlignmentEnum.VIOLENT},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(kkk);

        GroupCard l4Society = new GroupCard(
                "L4 Society",
                group,
                1, 0, 2, 0,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.WEIRD},
                Table.SpecialAbilityEnum.PLUSFOURFORDIRECTCONTROLNEUTRALIZATIONORDESTRUCTIONOFORBITALMINDCONTROLLASERS
        );
        cards.add(l4Society);

        GroupCard libertarians = new GroupCard(
                "Libertarians",
                group,
                1, 0, 4, 1,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.FANATIC},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(libertarians);

        GroupCard loanSharks = new GroupCard(
                "Loan Sharks",
                group,
                5, 5, 0, 6,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.CRIMINAL, Table.AlignmentEnum.VIOLENT},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(loanSharks);

        GroupCard localPoliceDepartments = new GroupCard(
                "Local Police Departments",
                group,
                0, 0, 4, 1,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.CONSERVATIVE, Table.AlignmentEnum.STRAIGHT, Table.AlignmentEnum.VIOLENT},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(localPoliceDepartments);

        GroupCard madisonAvenue = new GroupCard(
                "Madison Avenue",
                group,
                3, 3, 3, 2,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.NONE},
                Table.SpecialAbilityEnum.PLUSFIVEONANYATTEMPTTOCONTROLBIGMEDIAOREMPTYVEE
        );
        cards.add(madisonAvenue);

        GroupCard theMafia = new GroupCard(
                "The Mafia",
                group,
                7, 0, 7, 6,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.CRIMINAL, Table.AlignmentEnum.VIOLENT},
                Table.SpecialAbilityEnum.PLUSTHREEFORDIRECTCONTROLFORANYCRIMINALGROUP
        );
        cards.add(theMafia);

        GroupCard theMenInBlack = new GroupCard(
                "The Men In Black",
                group,
                0, 2, 6, 1,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.CRIMINAL, Table.AlignmentEnum.WEIRD},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(theMenInBlack);

        GroupCard militia = new GroupCard(
                "Militia",
                group,
                2, 0, 4, 2,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.VIOLENT, Table.AlignmentEnum.CONSERVATIVE},
                Table.SpecialAbilityEnum.PLUSSIXONANYATTEMPTTODESTROYANYCOMMUNISTGROUP
        );
        cards.add(militia);

        GroupCard moonies = new GroupCard(
                "Moonies",
                group,
                2, 0, 4, 3,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.PEACEFULL, Table.AlignmentEnum.FANATIC},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(moonies);

        GroupCard moralMinority = new GroupCard(
                "Moral Minority",
                group,
                2, 0, 1, 2,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.CONSERVATIVE, Table.AlignmentEnum.STRAIGHT, Table.AlignmentEnum.FANATIC},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(moralMinority);

        GroupCard morticians = new GroupCard(
                "Morticians",
                group,
                0, 0, 4, 1,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.STRAIGHT, Table.AlignmentEnum.PEACEFULL},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(morticians);

        GroupCard multinationalOilCompanies = new GroupCard(
                "Multinational Oil Companies",
                group,
                6, 0, 4, 8,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.NONE},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(multinationalOilCompanies);

        GroupCard nephewsOfGod = new GroupCard(
                "Nephews of God",
                group,
                0, 0, 4, 2,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.CONSERVATIVE, Table.AlignmentEnum.FANATIC},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(nephewsOfGod);

        GroupCard newYork = new GroupCard(
                "New York",
                group,
                7, 0, 8, 3,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.VIOLENT, Table.AlignmentEnum.CRIMINAL, Table.AlignmentEnum.GOVERNMENT},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(newYork);

        GroupCard nuclearPowerCompanies = new GroupCard(
                "Nuclear Power Plants",
                group,
                4, 0, 4, 3,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.CONSERVATIVE},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(nuclearPowerCompanies);

        GroupCard orbitalMindControlLasers = new GroupCard(
                "Orbital Mind Control Lasers",
                group,
                4, 2, 5, 0,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.COMMUNIST},
                Table.SpecialAbilityEnum.ONHISTURNOWNERCANADDREMOVEORREVERSEANALIGNMENTOFANYONEOTHERGROUPINPLAYCHANGELASTSFORTHATTURNONLY
        );
        cards.add(orbitalMindControlLasers);

        GroupCard parentTeacherAgglomeration = new GroupCard(
                "Parent Teacher Agglomeration",
                group,
                0, 0, 5, 1,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.PEACEFULL, Table.AlignmentEnum.CONSERVATIVE, Table.AlignmentEnum.STRAIGHT},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(parentTeacherAgglomeration);

        GroupCard pentagon = new GroupCard(
                "Pentagon",
                group,
                6, 0, 6, 2,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.GOVERNMENT, Table.AlignmentEnum.VIOLENT, Table.AlignmentEnum.STRAIGHT},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(pentagon);

        GroupCard thePhoneCompany = new GroupCard(
                "The Phone Company",
                group,
                5, 2, 6, 3,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.NONE},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(thePhoneCompany);

        GroupCard phonePhreaks = new GroupCard(
                "Phone Phreaks",
                group,
                0, 1, 1, 1,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.CRIMINAL, Table.AlignmentEnum.LIBERAL},
                Table.SpecialAbilityEnum.PLUSTHREEONANYATTEMPTTOCONTROLNEUTRALIZEORDESTORYTHEPHONECOMPANY
        );
        cards.add(phonePhreaks);

        GroupCard postOffice = new GroupCard(
                "Post Office",
                group,
                4, 3, 3, 1,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.GOVERNMENT},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(postOffice);

        GroupCard professionalSports = new GroupCard(
                "Professional Sports",
                group,
                2, 0, 4, 3,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.VIOLENT, Table.AlignmentEnum.FANATIC},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(professionalSports);

        GroupCard psychiatrists = new GroupCard(
                "Psychiatrists",
                group,
                0, 0, 6, 2,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.WEIRD},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(psychiatrists);

        GroupCard punkRockers = new GroupCard(
                "Punk Rockers",
                group,
                0, 0, 4, 1,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.WEIRD},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(punkRockers);

        GroupCard recyclers = new GroupCard(
                "Recyclers",
                group,
                2, 0, 2, 3,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.LIBERAL},
                Table.SpecialAbilityEnum.PAYFIVEMEGABUCKSFORTHISGROUPTODRAWANEXTRACARDONYOURTURNTHISTURNISNOTANACTION
        );
        cards.add(recyclers);

        GroupCard republicans = new GroupCard(
                "Republicans",
                group,
                4, 0, 4, 4,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.CONSERVATIVE},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(republicans);

        GroupCard robotSeaMonsters = new GroupCard(
                "Robot Sea Monsters",
                group,
                0, 0, 6, 2,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.VIOLENT, Table.AlignmentEnum.COMMUNIST},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(robotSeaMonsters);

        GroupCard scienceFictionFans = new GroupCard(
                "Science Fiction Fans",
                group,
                0, 0, 5, 1,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.WEIRD},
                Table.SpecialAbilityEnum.PLUSTWOONANYATTEMPTTOCONTROLANYWEIRDGROUP
        );
        cards.add(scienceFictionFans);

        GroupCard semiconcsciousLiberationArmy = new GroupCard(
                "Semiconscious Liberation Army",
                group,
                0, 0, 8, 0,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.CRIMINAL, Table.AlignmentEnum.VIOLENT, Table.AlignmentEnum.LIBERAL, Table.AlignmentEnum.WEIRD, Table.AlignmentEnum.COMMUNIST},
                Table.SpecialAbilityEnum.PLUSONEONANYATTEMPTTODESTROYANYGROUP
        );
        cards.add(semiconcsciousLiberationArmy);

        GroupCard smof = new GroupCard(
                "SMOF",
                group,
                1, 0, 1, 0,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.WEIRD},
                Table.SpecialAbilityEnum.PLUSFIVEFORDIRECTCONTROLOFSFFANSPLUSTWOFORDIRECTCONTROLOFTREKKIES
        );
        cards.add(smof);

        GroupCard societyForCreativeAnarchism = new GroupCard(
                "Society for Creative Anarchism",
                group,
                0, 0, 4, 1,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.VIOLENT, Table.AlignmentEnum.WEIRD},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(societyForCreativeAnarchism);

        GroupCard southAmericanNazis = new GroupCard(
                "South America Nazis",
                group,
                4, 0, 6, 2,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.CONSERVATIVE, Table.AlignmentEnum.VIOLENT},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(southAmericanNazis);

        GroupCard survivalists = new GroupCard(
                "Survivalists",
                group,
                0, 0, 6, 2,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.CONSERVATIVE, Table.AlignmentEnum.VIOLENT, Table.AlignmentEnum.FANATIC},
                Table.SpecialAbilityEnum.PLUSTWORESISTANCETOALLOWNERSOTHERGROUPS
        );
        cards.add(survivalists);

        GroupCard tabloids = new GroupCard(
                "Tabloids",
                group,
                2, 0, 3, 3,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.WEIRD},
                Table.SpecialAbilityEnum.PLUSTHREEFORDIRECTCONTROLOFCONVENIENCESTORES
        );
        cards.add(tabloids);

        GroupCard texas = new GroupCard(
                "Texas",
                group,
                6, 0, 6, 4,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.VIOLENT, Table.AlignmentEnum.CONSERVATIVE, Table.AlignmentEnum.GOVERNMENT},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(texas);

        GroupCard tobaccoAndLiquorCompanies = new GroupCard(
                "Tobacco And Liquor Companies",
                group,
                4, 0, 3, 3,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.STRAIGHT},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(tobaccoAndLiquorCompanies);

        GroupCard trekkies = new GroupCard(
                "Trekkies",
                group,
                0, 0, 4, 3,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.WEIRD, Table.AlignmentEnum.FANATIC},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(trekkies);

        GroupCard triliberalCommission = new GroupCard(
                "Trileberal Commission",
                group,
                5, 0, 6, 3,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.LIBERAL, Table.AlignmentEnum.STRAIGHT},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(triliberalCommission);

        GroupCard tvPreachers = new GroupCard(
                "TV Preachers",
                group,
                3, 0, 6, 4,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.STRAIGHT, Table.AlignmentEnum.FANATIC},
                Table.SpecialAbilityEnum.PLUSTHREEFORDIRECTCONTROLOFTHEMORALMINORITY
        );
        cards.add(tvPreachers);

        GroupCard undergroundNewsPapers = new GroupCard(
                "Underground Newspapers",
                group,
                1, 1, 5, 0,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.COMMUNIST, Table.AlignmentEnum.LIBERAL},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(undergroundNewsPapers);

        GroupCard theUnitedNations = new GroupCard(
                "The United Nations",
                group,
                1, 0, 3, 3,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.LIBERAL},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(theUnitedNations);

        GroupCard videoGames = new GroupCard(
                "Video Games",
                group,
                2, 0, 3, 7,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.NONE},
                Table.SpecialAbilityEnum.PLUSTHREEFORDIRECTCONTROLOFCONVENIENCESTORES
        );
        cards.add(videoGames);

        GroupCard yuppies = new GroupCard(
                "Yuppies",
                group,
                1, 1, 4, 5,
                new Table.AlignmentEnum[] {Table.AlignmentEnum.CONSERVATIVE},
                Table.SpecialAbilityEnum.NONE
        );
        cards.add(yuppies);
    }

    private void initializeSpecialCards() {

        //to be added to a database

        SpecialCard assassination = new SpecialCard(
                "Assassination",
                Table.CardTypeEnum.SPECIAL,
                "Play this card immediately after the dice are rolled on nay attempt (by andy palyer) to destroy, conrtol, or neutralize. That roll is immediately changed, retroactively to 2"
        );
        cards.add(assassination);

        SpecialCard bribery = new SpecialCard(
                "Bribery",
                Table.CardTypeEnum.SPECIAL,
                "Play this card during your turn to automatically take control of any one uncontrolled group. Playing this card counts as an action"
        );
        cards.add(bribery);

        SpecialCard computerEspionage = new SpecialCard(
                "Computer Espionage",
                Table.CardTypeEnum.SPECIAL,
                "Play this card at any time to either count the money on any one group card OR examine all of one player's special cards"
        );
        cards.add(computerEspionage);

        SpecialCard deepAgent = new SpecialCard(
                "Deep Agent",
                Table.CardTypeEnum.SPECIAL,
                "Play this card after privilege has been invoked. The privilege is totally abolished. That attack cannot be made privileged."
        );
        cards.add(deepAgent);

        SpecialCard interference1 = new SpecialCard(
                "Interference1",
                Table.CardTypeEnum.SPECIAL,
                "You may interfere with one privileged attack. No other players may interfere."
        );
        cards.add(interference1);

        SpecialCard interference2 = new SpecialCard(
                "Interference2",
                Table.CardTypeEnum.SPECIAL,
                "You may interfere with one privileged attack. No other players may interfere."
        );
        cards.add(interference2);

        SpecialCard marketManipulation = new SpecialCard(
                "Market Manipulation",
                Table.CardTypeEnum.SPECIAL,
                "Play this card during your income phase to double all your groups' incomes, for that turn only. This card does not allow the I.R.S. to collect twice, or require that the Post Office to pay twice."
        );
        cards.add(marketManipulation);

        SpecialCard mediaCampaign = new SpecialCard(
                "Media Campaign",
                Table.CardTypeEnum.SPECIAL,
                "Play this card at any time to revive a group from the 'dead' pile. It becomes uncontrolled. (If the Servants of Cthulhu destroyed the group, it still counts as a destroyed group for victory. If they destroy it again, it counts again!)"
        );
        cards.add(mediaCampaign);

        SpecialCard murphysLaw = new SpecialCard(
                "Murphys Law",
                Table.CardTypeEnum.SPECIAL,
                "Play this card immediately after the dice are rolled on any attempt (by any player) to destroy, control, or neutralize. That roll is immediately changed, retroactively to 12."
        );
        cards.add(murphysLaw);

        SpecialCard secretsManWasNotMeantToKnow = new SpecialCard(
                "Secrets Man Was Not Meant To Know",
                Table.CardTypeEnum.SPECIAL,
                "Play this card when any other Special card is played, for ANY purpose. That card is immediately neutralized; it has no affect. Both cards are discarded"
        );
        cards.add(secretsManWasNotMeantToKnow);

        SpecialCard senateInvestigatingCommittee = new SpecialCard(
                "Senate Investigating Committee",
                Table.CardTypeEnum.SPECIAL,
                "Play this card at the beginning of any other player's turn. That player loses his turn completely."
        );
        cards.add(senateInvestigatingCommittee);

        SpecialCard slushFund = new SpecialCard(
                "Slush Fund",
                Table.CardTypeEnum.SPECIAL,
                "Exchange this card, at any time, for 15MB to be placed in your Illuminati treasury"
        );
        cards.add(slushFund);

        SpecialCard swissBankAccount = new SpecialCard(
                "Swiss Bank Account",
                Table.CardTypeEnum.SPECIAL,
                "Exchange this card, at any time, for 25MB to be placed in your Illuminati treasury"
        );
        cards.add(swissBankAccount);

        SpecialCard whisperingCampaign = new SpecialCard(
                "Whispering Campaign",
                Table.CardTypeEnum.SPECIAL,
                "You may attempt to destroy a single group with Power 0. Roll attacking power vs. defending resistance, but a successful attack destroys the target. Playing this card is not an action, but the attack itself is an action"
        );
        cards.add(whisperingCampaign);

        SpecialCard whiteCollarCrime = new SpecialCard(
                "White Collar Crime",
                Table.CardTypeEnum.SPECIAL,
                "Play this card at any time to reorganize all your money freely - that is, any amount(s) may be moved between any groups. You also get any extra 5MB which may be placed anywhere"
        );
        cards.add(whiteCollarCrime);
    }
}
