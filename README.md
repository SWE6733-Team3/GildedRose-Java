# GildedRose-Java

An example refactoring of the "Gilded Rose Kata" Java edition, from:
https://github.com/emilybache/GildedRose-Refactoring-Kata/tree/main/Java

Some of the changes applied to the original code are:

    GildedRose.Java
        -Eliminated chains of nested if statements
        -Moved independent functionalities to individual methods
        -Utilize enhanced "for" to iterate
        -Applied good programming practices with regard to access permissions (properties/fields)
       
    Item.Java
        -Added "ages", "legendary", and "conjured" item properties
        -Applied good programming practices with regard to access permissions (properties/fields)
        -Improved formatting of toString method for easy to review outputs
        -Added specification of item properties based on the name of the item
        
Navigate to https://github.com/james-things/GildedRose-Java/tree/master/src/main/java/com/gildedrose to start browsing the code revisions.

FROM EASTON THIS IS HOW YOU GET AROUND THE SSL

add this - git -c http.sslVerify=false pull to your git functions