xquery version "3.1";

declare namespace users = "http://www.zis.rs/xml/schemes/users";
declare namespace user = "http://www.zis.rs/xml/schemes/user";

for $user in doc("/db/xml/schemes/users.xml")/users:users/user:user
where $user//@id = "%1$s"
return $user