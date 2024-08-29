//
//  ContentView.swift
//  GameifyIOS
//
//  Created by Samuel Muigai on 24/07/2024.
//

import UIKit
import SwiftUI
import shared


struct ComposeView:UIViewControllerRepresentable{
    func makeUIViewController(context: Context) -> some UIViewController {
        MainViewControllerKt.MainViewController()
    }
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
        
    }
}


struct ContentView: View {
    var body: some View {
        ComposeView()
            .ignoresSafeArea(edges: .all)
            .ignoresSafeArea(.keyboard)
    }
}


