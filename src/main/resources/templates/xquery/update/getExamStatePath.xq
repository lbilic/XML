xquery version "3.1";

declare namespace sp="http://www.zis.rs/schemes/exam_states";
declare namespace stp="http://www.zis.rs/schemes/exam_state";


for $state in fn:doc("/db/rs/zis/exam_states.xml")/sp:exam_states/stp:exam_state
where $state/@state != "kraj"
return fn:concat(fn:concat(fn:data($state/@patient), ","),fn:data($state/@state))