//
//  EmptyStateView.swift
//  iosApp
//
//  Created by Dzmitry Chyrta on 22.11.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct EmptyStateView: View {
        
    var title: String
    
    var body: some View {
        VStack {
            Text(title)
        }
    }
}

struct EmptyStateView_Previews: PreviewProvider {
    static var previews: some View {
        EmptyStateView(title: "Start your search now")
    }
}
