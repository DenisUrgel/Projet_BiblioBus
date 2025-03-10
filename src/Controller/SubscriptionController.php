<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;

final class SubscriptionController extends AbstractController
{

    #[Route('/registrer', name: 'app_registrer')]
    public function details(): Response
   {

       return $this->render('subscription.html.twig');
    } 
}
 

