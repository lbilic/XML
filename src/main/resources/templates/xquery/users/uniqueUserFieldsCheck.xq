xquery version "3.1";

declare namespace users = "http://www.zis.rs/xml/schemes/users";
declare namespace user = "http://www.zis.rs/xml/schemes/user";

<result> {
    for $user in doc("/db/rs/zis/users.xml")/users:users/user:user
    return if ($user//user:jmbg = "%1$s" and $user//user:username = "%2$s")
    then "JMBG i korisnicko ime vec postoje!"
    else if ($user//user:jmbg = "%1$s")
        then "JMBG vec postoji!"
        else if ($user//user:username = "%2$s")
            then "Korisnicko ime vec postoji!"
            else ()
}
</result>