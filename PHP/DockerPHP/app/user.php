<?php

class User 
{
    public $username;
    public $admin = false;

    public function __construct(string $username, bool $admin)
    {
        $this->username = $username;
        $this->admin = $admin;
    }
}

$user = new User("wiener", false);
$user_ser = serialize($user);

// Test deserialize
$test_obj = 'Tzo0OiJVc2VyIjoyOntzOjg6InVzZXJuYW1lIjtzOjY6IndpZW5lciI7czo1OiJhZG1pbiI7YjowO30%3d';
$test_obj_decoded = base64_decode(urldecode($test_obj));

print($test_obj_decoded . "\r\n");
print($user_ser . "\r\n");

$user_test = unserialize($test_obj_decoded);

print('Output objects' . "\r\n");

print($user_test->username  . "\r\n");

$user_test->admin = true;
$user_ser = serialize($user_test);
print(urlencode(base64_encode($user_ser)) . "\r\n");

?>