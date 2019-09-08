xquery version "3.1";

declare namespace users = "http://www.zis.rs/schemes/users";
declare namespace user = "http://www.zis.rs/schemes/user";

for $user in fn:doc("/db/rs/zis/users.xml")/users:users/user:user
where $user/@id = "%1$s"
return $user