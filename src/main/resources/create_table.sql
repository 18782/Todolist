CREATE TABLE `todotask` (
   `ID` int(11) NOT NULL AUTO_INCREMENT,
   `WORKNAME` varchar(35) NOT NULL,
   `STARTDATE` varchar(8)  NOT NULL,
   `ENDDATE` varchar(8)  NOT NULL,
   `STATUS` int(1) NOT NULL,
   PRIMARY KEY (`ID`)
 )